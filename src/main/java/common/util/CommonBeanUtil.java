package common.util;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

/**
 * Created by nibnait on 2020/12/31
 */
public class CommonBeanUtil extends BeanUtils {

    private static final ConcurrentMap<Class, MethodAccess> localCache = new ConcurrentHashMap<>();

    public static MethodAccess get(Class clazz) {
        if (localCache.containsKey(clazz)) {
            return localCache.get(clazz);
        }

        MethodAccess methodAccess = MethodAccess.get(clazz);
        localCache.putIfAbsent(clazz, methodAccess);
        return methodAccess;
    }

    public static <S, T> T copyProperties(S source, Supplier<T> targetSupplier) {
        T target = targetSupplier.get();
        if (source == null) {
            return target;
        }

        MethodAccess sourceMethodAccess = get(source.getClass());
        MethodAccess targetMethodAccess = get(target.getClass());
        Field[] declaredFields = source.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            String name = field.getName();
            Object value = sourceMethodAccess.invoke(source, "get" + StringUtils.capitalize(name), null);
            try {
                targetMethodAccess.invoke(target, "set" + StringUtils.capitalize(name), value);
            } catch (Exception e) {
                // source 中未设置 get 方法的字段，会直接跳过
                // target 中没有 source 中的字段，也会直接跳过
            }
        }

        return target;
    }

    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> targetSupplier) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T target = copyProperties(source, targetSupplier);
            list.add(target);
        }
        return list;
    }

}