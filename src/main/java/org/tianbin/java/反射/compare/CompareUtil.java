package org.tianbin.java.反射.compare;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * Created by nibnait on 2020/11/11
 */
public class CompareUtil {

    public static void compare(Object thisObj, Object thatObj, Map<String, String> errorMap) {

        try {
            Class<?> clazz = thisObj.getClass();
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                if (field.getModifiers() != Modifier.PRIVATE) {
                    continue;
                }

                // 排除静态字段
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), clazz);
                // 获得get方法
                Method getMethod = propertyDescriptor.getReadMethod();

                Object leftProperty = getMethod.invoke(thisObj);
                Object rightProperty = getMethod.invoke(thatObj);

                boolean pass = compareObject(leftProperty, rightProperty);
                if (!pass) {
                    addToErrMap(errorMap, leftProperty, rightProperty, thisObj.getClass().getSimpleName(), getMethod.getName());
                }
            }
        } catch (IntrospectionException e) {
            errorMap.put("比对", "核对发生IntrospectionException异常");
        } catch (IllegalAccessException e) {
            errorMap.put("比对", "核对发生IllegalAccessException异常");
        } catch (InvocationTargetException e) {
            errorMap.put("比对", "核对发生InvocationTargetException异常");
        }

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

        if (thisObj == null && thatObj == null) {
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
