package common.util.compare;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class JacksonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String SEPARATOR = ":";
    public static final String ARRAY_SIGN = "*";
    public static final String MINUS_SIGN = "\"-\"";
    public static final String ARRAY_SIGN_REPLACE = "#";
    public static final String PLACEHOLDER_SIGN = "{}";
    private static final String BLANK_REPLACE = " ";
    private static final String EMPTY = "";

    static {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MAPPER.setDateFormat(dateFormat);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static String replace(String json, String path, Object value) {
        if (StringUtils.isBlank(path)) {
            return json;
        }
        // 使用 # 替换, 否则会因为正则导致替换失败
        path = path.replace(ARRAY_SIGN, ARRAY_SIGN_REPLACE);
        // 判断是否多级路径
        int lastNodeIndex = path.lastIndexOf(SEPARATOR);
        if (lastNodeIndex == -1) {
            return replaceField(json, path, path, value);
        }
        // 判断是否需要循环解析
        if (!path.contains(ARRAY_SIGN_REPLACE)) {
            return replaceField(json, path, path.substring(lastNodeIndex + 1), value);
        }
        // 需要循环解析, 递归
        String prePath = path.substring(0, getArraySignIndex(path));
        String prePathValue = focus(json, prePath);
        JsonNode prePathJsonNode = json2Node(prePathValue);
        for (int i = 0; i < prePathJsonNode.size(); i++) {
            json = replace(json, path.replaceFirst(ARRAY_SIGN_REPLACE, i + ""), value);
        }
        return json;
    }

    private static int getArraySignIndex(String path) {
        return path.indexOf(ARRAY_SIGN_REPLACE) == 0 ? 0 : path.indexOf(ARRAY_SIGN_REPLACE) - 1;
    }

    public static String focus(String json, String path) {
        if (StringUtils.isBlank(path)) {
            return json;
        }
        // 使用 # 替换, 否则会因为正则导致替换失败
        path = path.replace(ARRAY_SIGN, ARRAY_SIGN_REPLACE);
        // 判断是否多级路径
        int lastNodeIndex = path.lastIndexOf(SEPARATOR);
        if (lastNodeIndex == -1) {
            return focusField(json, path);
        }
        // 判断是否需要循环解析
        if (!path.contains(ARRAY_SIGN_REPLACE)) {
            return focusField(json, path);
        }
        // 需要循环解析, 递归
        List<Object> result = new ArrayList<>();
        String prePath = path.substring(0, getArraySignIndex(path));
        String prePathValue = focus(json, prePath);
        JsonNode prePathJsonNode = json2Node(prePathValue);

        if (prePathJsonNode == null) {
            return MINUS_SIGN;
        }

        for (int i = 0; i < prePathJsonNode.size(); i++) {
            String str = focus(json, path.replaceFirst(ARRAY_SIGN_REPLACE, i + ""));
            result.add(str);
        }
        Object[] objects = result.toArray();
        // 自然排序
        Arrays.sort(objects);
        return JSON.toJSONString(objects).replaceAll(BLANK_REPLACE, EMPTY);
    }

    private static String focusField(String json, String path) {
        JsonNode jsonNode = json2Node(json);
        String[] paths = path.split(SEPARATOR);
        for (String fieldName : paths) {
            if (jsonNode == null) {
                continue;
            }
            if (jsonNode.isArray()) {
                jsonNode = jsonNode.get(Integer.parseInt(fieldName));
            } else {
                jsonNode = jsonNode.get(fieldName);
            }
        }
        return node2Json(jsonNode);
    }

    private static String replaceField(String json, String path, String key, Object value) {
        if (StringUtils.isBlank(path)) {
            return json;
        }
        JsonNode jsonNode = json2Node(json);
        JsonNode temp = jsonNode;
        String[] paths = path.split(SEPARATOR);
        for (String fieldName : paths) {
            if (temp == null) {
                continue;
            }
            // 找到 key
            if (fieldName.equals(key)) {
                if (temp.isArray()) {
                    JsonNode removeJsonNode = ((ArrayNode) temp).remove(Integer.parseInt(fieldName));
                    if (value != null && removeJsonNode != null) {
                        Class<?> valueClazz = value.getClass();
                        if (valueClazz == String.class) {
                            ((ArrayNode) temp).insert(Integer.parseInt(fieldName), (String) value);
                        } else if (valueClazz == Boolean.class) {
                            ((ArrayNode) temp).insert(Integer.parseInt(fieldName), (Boolean) value);
                        } else if (valueClazz == Short.class) {
                            ((ArrayNode) temp).insert(Integer.parseInt(fieldName), (Short) value);
                        } else if (valueClazz == Integer.class) {
                            ((ArrayNode) temp).insert(Integer.parseInt(fieldName), (Integer) value);
                        } else if (valueClazz == Double.class) {
                            ((ArrayNode) temp).insert(Integer.parseInt(fieldName), (Double) value);
                        } else if (valueClazz == Long.class) {
                            ((ArrayNode) temp).insert(Integer.parseInt(fieldName), (Long) value);
                        } else if (valueClazz == Float.class) {
                            ((ArrayNode) temp).insert(Integer.parseInt(fieldName), (Float) value);
                        } else if (valueClazz == BigDecimal.class) {
                            ((ArrayNode) temp).insert(Integer.parseInt(fieldName), (BigDecimal) value);
                        }
                    }
                } else {
                    JsonNode removeJsonNode = ((ObjectNode) temp).remove(fieldName);
                    if (value != null && removeJsonNode != null) {
                        Class<?> valueClazz = value.getClass();
                        if (valueClazz == String.class) {
                            ((ObjectNode) temp).put(fieldName, (String) value);
                        } else if (valueClazz == Boolean.class) {
                            ((ObjectNode) temp).put(fieldName, (Boolean) value);
                        } else if (valueClazz == Short.class) {
                            ((ObjectNode) temp).put(fieldName, (Short) value);
                        } else if (valueClazz == Integer.class) {
                            ((ObjectNode) temp).put(fieldName, (Integer) value);
                        } else if (valueClazz == Double.class) {
                            ((ObjectNode) temp).put(fieldName, (Double) value);
                        } else if (valueClazz == Long.class) {
                            ((ObjectNode) temp).put(fieldName, (Long) value);
                        } else if (valueClazz == Float.class) {
                            ((ObjectNode) temp).put(fieldName, (Float) value);
                        } else if (valueClazz == BigDecimal.class) {
                            ((ObjectNode) temp).put(fieldName, (BigDecimal) value);
                        }
                    }
                }
                continue;
            }
            if (temp.isArray()) {
                temp = temp.get(Integer.parseInt(fieldName));
            } else {
                temp = temp.get(fieldName);
            }
        }
        return node2Json(jsonNode);
    }

    private static String node2Json(JsonNode jsonNode) {
        if (jsonNode == null) {
            return null;
        }
        String value = jsonNode.asText();
        if (StringUtils.isBlank(value)) {
            value = jsonNode.toString();
        }
        return value;
    }

    private static JsonNode json2Node(String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }

        JsonNode tempNode = null;
        try {
            tempNode = MAPPER.readTree(json);
        } catch (Exception e) {
            log.error("json2Node error json {}", json, e);
        }
        return tempNode;
    }

    public static boolean isJson(String json) {
        try {
            MAPPER.readTree(json);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
