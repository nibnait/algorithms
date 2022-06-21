package cc.tianbin.java.反射;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nibnait on 2020/11/11
 */
@Deprecated
public class CompareUtil {

    private static final Log log = LogFactory.get(CompareUtil.class);

    /**
     * 返回 true/false
     * @param whiteFiledList 不需要核对的字段
     */
    public static boolean compareBool(Object leftObj, Object rightObj, List<String> whiteFiledList) {
        Map<String, String> errorMap = compare(leftObj, rightObj, whiteFiledList);

        if (MapUtils.isNotEmpty(errorMap)) {
            log.info("CompareUtils.errorMap: {}, leftObj: {}, rightObj: {}",
                    JSON.toJSONString(errorMap),
                    JSON.toJSONString(leftObj), JSON.toJSONString(rightObj));
        }
        return MapUtils.isEmpty(errorMap);
    }

    /**
     * 返回 errorMap
     * @param whiteFiledList 不需要核对的字段
     */
    public static Map<String, String> compare(Object leftObj, Object rightObj, List<String> whiteFiledList) {
        Map<String, String> errorMap = new HashMap<>();
        try {
            PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(leftObj.getClass());

            if (propertyDescriptors == null || propertyDescriptors.length <= 1) {
                return errorMap;
            }

            for (int i = 1; i < propertyDescriptors.length; i++) {
                PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
                String fieldName = propertyDescriptor.getName();

                if (whiteFiledList.contains(fieldName)) {
                    continue;
                }

                // 获得get方法
                Method readMethod = propertyDescriptor.getReadMethod();

                if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                    readMethod.setAccessible(true);
                }

                Object leftProperty = readMethod.invoke(leftObj);
                Object rightProperty = readMethod.invoke(rightObj);

                boolean pass = compareObject(leftProperty, rightProperty);
                if (!pass) {
                    addToErrMap(errorMap, leftProperty, rightProperty, leftObj.getClass().getSimpleName(), fieldName);
                }

            }

        } catch (Exception e) {
            log.error(e, "CompareUtils error " + e.getMessage());
            errorMap.put("比对", "核对发生异常");
        }

        return errorMap;
    }

    private static void addToErrMap(Map<String, String> errorMap, Object leftProperty, Object rightProperty, String className, String fieldName) {
        StringBuilder sb = new StringBuilder();
        sb.append("leftValue: ");
        sb.append(leftProperty != null ? leftProperty.toString() : "null");
        sb.append(";");
        sb.append("rightValue: ");
        sb.append(rightProperty != null ? rightProperty.toString() : "null");

        String key = className + "." + fieldName;
        errorMap.put(key, sb.toString());

    }

    private static boolean compareObject(Object thisObj, Object thatObj) {

        if ((thisObj == null && thatObj != null) || (thisObj != null && thatObj == null)) {
            return false;
        }

        if (thisObj == null) {
            return true;
        }

        if (!thisObj.getClass().equals(thatObj.getClass())) {
            return false;
        }

        if (thisObj instanceof Number && thisObj instanceof Comparable) {
            return ((Comparable) thisObj).compareTo(thatObj) == 0;
        }

        return thisObj.equals(thatObj);
    }

}
