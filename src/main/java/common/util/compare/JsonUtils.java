package common.util.compare;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.json.comparison.JsonCompare;
import com.json.comparison.JsonComparisonResult;
import com.json.comparison.comprator.model.api.FieldComparison;
import common.bo.compare.CompareBO;
import common.bo.compare.CompareResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.helpers.MessageFormatter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class JsonUtils {

    public static CompareResultDTO diffJsonObject(JSONObject actualObject, JSONObject expectObject, Set<String> focusFields) {
        CompareResultDTO compareResultDTO = new CompareResultDTO();
        if (CollectionUtils.isEmpty(focusFields)) {
            compareResultDTO.setMatch(true);
            return compareResultDTO;
        }

        List<CompareResultDTO.FieldComparison> modifiedFields = Lists.newArrayList();
        for (String key : focusFields) {
            String actual = actualObject.getString(key);
            String expect = expectObject.getString(key);
            if (!CompareUtils.matchObject(actual, expect)) {
                addModifyFields(key, actual, expect, modifiedFields);
            }
        }

        compareResultDTO.setMatch(CollectionUtils.isEmpty(modifiedFields));
        compareResultDTO.setModifiedFields(modifiedFields);
        return compareResultDTO;
    }

    private static void addModifyFields(String key, String actual, String expect, List<CompareResultDTO.FieldComparison> modifiedFields) {
        CompareResultDTO.FieldComparison comparison = new CompareResultDTO.FieldComparison();
        comparison.setField(key);
        comparison.setExpected(expect);
        comparison.setActual(actual);

        modifiedFields.add(comparison);
    }

    public static CompareResultDTO diffStr(String actualStr, String expectStr, CompareBO compareBO) {
        // 判断是不是标准 json, 是 json 则不转, 否则会发生转义
        if (!JacksonUtils.isJson(actualStr)) {
            try {
                actualStr = JSON.toJSONString(JSON.parseObject(actualStr), SerializerFeature.WriteMapNullValue);
            } catch (Exception e) {
                // ignore
            }
        }
        if (!JacksonUtils.isJson(expectStr)) {
            try {
                expectStr = JSON.toJSONString(JSON.parseObject(expectStr), SerializerFeature.WriteMapNullValue);
            } catch (Exception e) {
                // ignore
            }
        }
        return diffStr0(actualStr, expectStr, compareBO);
    }

    private static CompareResultDTO diffStr0(String actualStr, String expectStr, CompareBO compareBO) {
        CompareResultDTO compareResultDTO = new CompareResultDTO();
        compareResultDTO.setMatch(true);

        // 如果不是 json 数据, 直接当成字符串匹配
        if (!JacksonUtils.isJson(actualStr) || !JacksonUtils.isJson(expectStr)) {
            compareResultDTO.setMatch(actualStr.equals(expectStr));
        }
        Set<CompareBO.OptimizationType> optimizations = compareBO.getOptimizations();
        Set<String> focusFields = compareBO.getFocusFields();
        Set<String> excludeFields = compareBO.getExcludeFields();
        // 1. 系统默认优化项: 如果开启了则添加到聚焦字段
        for (CompareBO.OptimizationType optimization : optimizations) {
            // 如果是分页插件, 则只关注 data:list 数据内容
            if (optimization == CompareBO.OptimizationType.PAGE_HELPER) {
                // 判断是否满足条件, 以是否包含 navigatePages、pageSize 为标准
                if (expectStr.contains("navigatePages") && expectStr.contains("pageSize")) {
                    focusFields.add("list");
                }
            }
        }
        // 2. 排除字段: 如果有先把指定字段移除
        if (excludeFields.size() > 0) {
            for (String excludeField : excludeFields) {
                actualStr = JacksonUtils.replace(actualStr, excludeField, null);
                expectStr = JacksonUtils.replace(expectStr, excludeField, null);
            }
        }
        // 3. 无聚焦字段: 直接匹配字符串
        if (focusFields.size() <= 0) {
            return jsonEqual(actualStr, expectStr, compareBO);
        }
        // 4. 有聚焦字段: 取出依次比较
        ArrayList<CompareResultDTO.FieldComparison> modifiedFields = Lists.newArrayList();
        ArrayList<CompareResultDTO.FieldComparison> missingFields = Lists.newArrayList();
        ArrayList<CompareResultDTO.FieldComparison> newFields = Lists.newArrayList();
        for (String focusField : focusFields) {
            String actualValue = JacksonUtils.focus(actualStr, focusField);
            String expectValue = JacksonUtils.focus(expectStr, focusField);

            compareBO.setCurrentField(focusField);
            CompareResultDTO currentFieldCompareResult = jsonEqual(actualValue, expectValue, compareBO);

            if (!currentFieldCompareResult.isMatch()) {
                compareResultDTO.setMatch(false);
            }
            modifiedFields.addAll(currentFieldCompareResult.getModifiedFields());
            missingFields.addAll(currentFieldCompareResult.getMissingFields());
            newFields.addAll(currentFieldCompareResult.getNewFields());
        }
        // 所有聚焦字段比较完后, 直接返回 true
        compareResultDTO.setModifiedFields(modifiedFields);
        compareResultDTO.setMissingFields(missingFields);
        compareResultDTO.setNewFields(newFields);

        return compareResultDTO;
    }

    public static CompareResultDTO jsonEqual(String actual, String expect, CompareBO compareBO) {
        // 5. 先用 json 判断是否相等
        JsonComparisonResult comparisonResult = compare(actual, expect, compareBO);

        CompareResultDTO compareResultDTO = new CompareResultDTO(false);
        if (comparisonResult == null) {
            return compareResultDTO;
        }

        compareResultDTO.setMatch(comparisonResult.isMatch());
        compareResultDTO.setModifiedFields(getFieldComparisonList(compareBO.getCurrentField(), comparisonResult.getModifiedFields()));
        compareResultDTO.setMissingFields(getFieldComparisonList(compareBO.getCurrentField(), comparisonResult.getMissingFields()));
        compareResultDTO.setNewFields(getFieldComparisonList(compareBO.getCurrentField(), comparisonResult.getNewFields()));

        // 如果全部比对成功, 直接返回 true
        if (comparisonResult.isMatch()) {
            compareResultDTO.setMatch(true);
        }
        // 6. 如果有修改字段, 直接返回失败
        if (CollectionUtils.isNotEmpty(comparisonResult.getModifiedFields())) {
            compareResultDTO.setMatch(false);
            return compareResultDTO;
        }
        // 7. 程序走到这里, 表明有两种情况, 一种是新增了字段, 一种是丢失了字段, 所以需要判断是否开启了忽略功能
        // 7.1 如果全部开启, 直接返回 true
        if (compareBO.getOptimizations().contains(CompareBO.OptimizationType.IGNORE_NEW_FIELDS) &&
                compareBO.getOptimizations().contains(CompareBO.OptimizationType.IGNORE_MISSING_FIELDS)) {
            compareResultDTO.setMatch(true);
        }
        // 7.2 只开启忽略新增字段, 那么只需要判断是否有丢失字段即可
        else if (compareBO.getOptimizations().contains(CompareBO.OptimizationType.IGNORE_NEW_FIELDS)) {
            compareResultDTO.setMatch(CollectionUtils.isEmpty(comparisonResult.getMissingFields()));
        }
        // 7.3 只开启忽略丢失字段, 那么只需要判断新增字段即可
        else {
            compareResultDTO.setMatch(CollectionUtils.isEmpty(comparisonResult.getNewFields()));
        }

        return compareResultDTO;
    }

    private static JsonComparisonResult compare(String actual, String expect, CompareBO compareBO) {
        JsonComparisonResult comparisonResult = null;
        try {
            comparisonResult = JsonCompare.builder().build().compare(expect, actual);
        } catch (Exception e) {
            log.error("JsonUtils.jsonEqual error", e);
        }

        if (comparisonResult == null || !comparisonResult.isMatch()) {
            log.info("JsonUtils.jsonEqual false compareBO:{}", JSON.toJSONString(compareBO));
            log.info("JsonUtils.jsonEqual false actual:{}", actual);
            log.info("JsonUtils.jsonEqual false expect:{}", expect);
        }
        return comparisonResult;
    }

    private static List<CompareResultDTO.FieldComparison> getFieldComparisonList(String currentField, Collection<FieldComparison> modifiedFieldList) {
        return modifiedFieldList.stream()
                .map(comparison -> convertToFieldComparison(currentField, comparison))
                .collect(Collectors.toList());
    }

    private static CompareResultDTO.FieldComparison convertToFieldComparison(String currentField, FieldComparison comparison) {
        CompareResultDTO.FieldComparison fieldComparison = new CompareResultDTO.FieldComparison();

        try {
            Integer[] indexArray = getIndexArray(comparison.getField());
            currentField = currentField.replace(JacksonUtils.ARRAY_SIGN, JacksonUtils.PLACEHOLDER_SIGN);
            fieldComparison.setField(MessageFormatter.arrayFormat(currentField, indexArray).getMessage());
        } catch (Exception e) {
            if (StringUtils.isBlank(currentField)) {
                currentField = comparison.getField();
            }
            if ("list".equals(currentField)) {
                currentField = currentField + comparison.getField();
            }
            fieldComparison.setField(currentField);
        }

        fieldComparison.setExpected(comparison.getExpected());
        fieldComparison.setActual(comparison.getActual());
        return fieldComparison;
    }

    private static Integer[] getIndexArray(String field) {
        JSONArray jsonArray;
        try {
            jsonArray = JSON.parseArray(field);
            Integer[] indexArray = new Integer[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); i++) {
                indexArray[i] = (int) jsonArray.get(i);
            }
            return indexArray;
        } catch (Exception e) {
            field = "[" + field + "]";
            jsonArray = JSON.parseArray(field);
            Integer[] indexArray = new Integer[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONArray arr = (JSONArray) jsonArray.get(i);
                indexArray[i] = (int) arr.get(0);
            }
            return indexArray;
        }


    }

}
