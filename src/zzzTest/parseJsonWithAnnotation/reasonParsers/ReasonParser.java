package zzzTest.parseJsonWithAnnotation.reasonParsers;

import utils.StringUtil;
import zzzTest.parseJsonWithAnnotation.abstractClass.ReasonParserConstants;
import zzzTest.parseJsonWithAnnotation.abstractClass.ReasonTitle;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

public abstract class ReasonParser<T> {

    /**
     * 泛型类
     */
    protected Class clazz;
    protected String FLAG = "flag";
    protected String bool_true = "true";
    protected String bool_false = "false";


    public ReasonParser() {
        //传入的泛型参数
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class) type.getActualTypeArguments()[0];
    }


    public Map<String, String> parseReason(String value) {
        Map<String, String> resultMap = new HashMap<>();
        String[] reasonMap = value.split(";");
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < reasonMap.length; i++) {
            resultMap.put(FLAG, bool_true);
            resultMap.putAll(putInResultMap(reasonMap[i], fields));
            if (resultMap.get(FLAG).equals(bool_true)) { //如果reason[0]不属于新的 main_reason，即老数据直接放到"OTHER"中
                putInOther(resultMap, reasonMap[i]);
            }
        }
        addEmptyValue(fields, resultMap);   //添加 空对象
        return resultMap;
    }

    private Map<String, String> putInResultMap(String reasonEntry, Field[] fields) {
        Map<String, String> resultMap = new HashMap<>();
        String[] reason = reasonEntry.split(":");
        for (Field field : fields) {
            if (field.isAnnotationPresent(ReasonTitle.class)) {
                ReasonTitle reasonTitle = field.getAnnotation(ReasonTitle.class);
                if (reason.length>1) {
                    if (reason[0].equals(reasonTitle.value())) {
                        resultMap.put(FLAG, bool_false);
                        if (reasonTitle.value().equals(ReasonParserConstants.OTHER)) {
                            putInOther(resultMap, reasonEntry);
                            break;
                        }
                        if (reasonTitle.value().equals(ReasonParserConstants.CUSTOM)) {
                            resultMap.put(ReasonParserConstants.CUSTOM, reasonEntry);
                            break;
                        }
                        String replace = reason[1].replace("||", "； ");
                        resultMap.put(field.getName(), replace);
                        break;
                    }
                } else {    //防止reasonMap[i] 没有被 ":" 分隔开
                    if (reasonEntry.contains(reasonTitle.value())) {
                        resultMap.put(FLAG, bool_false);
                        if (reasonTitle.value().equals(ReasonParserConstants.OTHER)) {
                            resultMap.put(ReasonParserConstants.OTHER, reasonEntry);
                            break;
                        }
                        if (reasonTitle.value().equals(ReasonParserConstants.CUSTOM)) {
                            resultMap.put(ReasonParserConstants.CUSTOM, reasonEntry);
                            break;
                        }
                        resultMap.put(field.getName(), reasonEntry);
                        break;
                    }
                }
            }
        }
        return resultMap;
    }

    private void addEmptyValue(Field[] fields, Map<String, String> resultMap) {
        for (Field field : fields) {
            String mapValue = resultMap.get(field.getName());
            if (StringUtil.isBlank(mapValue)) {
                resultMap.put(field.getName(), "");
            }
        }
    }

    private void putInOther(Map<String, String> resultMap, String str) {
        StringBuilder otherReason = new StringBuilder();
        if (StringUtil.isNotBlank(resultMap.get(ReasonParserConstants.OTHER))) {
            otherReason.append(resultMap.get(ReasonParserConstants.OTHER));
            otherReason.append("。  ");
        }
        resultMap.put(ReasonParserConstants.OTHER, otherReason.append(str).toString().replace("||", "； "));
    }
}
