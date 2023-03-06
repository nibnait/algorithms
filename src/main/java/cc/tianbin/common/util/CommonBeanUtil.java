package cc.tianbin.common.util;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.google.common.collect.Lists;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.collections4.CollectionUtils;
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

    public static <F, T> T copyProperties_BeanCopier(F source, Supplier<T> targetSupplier) {
        T target = targetSupplier.get();

        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);

        return target;
    }

    public static <F, T> T copyProperties_BeanUtils(F source, Supplier<T> targetSupplier) {
        T target = targetSupplier.get();

        BeanUtils.copyProperties(source, target);

        return target;
    }

    public static <F, T> T copyProperties_fieldGetSet(F source, Supplier<T> targetSupplier) {
        T target = targetSupplier.get();

        if (source == null) {
            return null;
        }
        Field[] fromDeclaredFields = source.getClass().getDeclaredFields();

        for (Field field : fromDeclaredFields) {
            try {
                field.setAccessible(true);

                Object value = field.get(source);
                field.set(target, value);
            } catch (Exception e) {
                // source 中未设置 get 方法的字段，会直接跳过
                // target 中没有 source 中的字段，也会直接跳过
            }
        }

        return target;
    }

    private static final ConcurrentMap<Class, MethodAccess> localCache = new ConcurrentHashMap<>();
    private static final ConcurrentMap<Class, Field[]> localCacheFields = new ConcurrentHashMap<>();

    public static MethodAccess get(Class clazz) {
        if (localCache.containsKey(clazz)) {
            return localCache.get(clazz);
        }

        MethodAccess methodAccess = MethodAccess.get(clazz);
        localCache.putIfAbsent(clazz, methodAccess);
        return methodAccess;
    }

    public static Field[] getFields(Class clazz) {
        if (localCacheFields.containsKey(clazz)) {
            return localCacheFields.get(clazz);
        }

        Field[] declaredFields = clazz.getDeclaredFields();
        localCacheFields.putIfAbsent(clazz, declaredFields);
        return declaredFields;
    }

    public static <S, T> T copyProperties_methodInvoke(S source, Supplier<T> targetSupplier) {
        T target = targetSupplier.get();
        if (source == null) {
            return target;
        }

        MethodAccess sourceMethodAccess = get(source.getClass());
        MethodAccess targetMethodAccess = get(target.getClass());
        Field[] declaredFields = source.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            try {
                String name = field.getName();
                Object value = sourceMethodAccess.invoke(source, "get" + StringUtils.capitalize(name), null);
                targetMethodAccess.invoke(target, "set" + StringUtils.capitalize(name), value);
            } catch (Exception e) {
                // source 中未设置 get 方法的字段，会直接跳过
                // target 中没有 source 中的字段，也会直接跳过
            }
        }

        return target;
    }

    public static <S, T> T copyProperties_methodInvoke_V2(S source, Supplier<T> targetSupplier) {
        T target = targetSupplier.get();
        if (source == null) {
            return target;
        }

        MethodAccess sourceMethodAccess = get(source.getClass());
        MethodAccess targetMethodAccess = get(target.getClass());
        Field[] declaredFields = getFields(source.getClass());
        for (Field field : declaredFields) {
            try {
                String name = field.getName();
                Object value = sourceMethodAccess.invoke(source, "get" + StringUtils.capitalize(name), null);
                targetMethodAccess.invoke(target, "set" + StringUtils.capitalize(name), value);
            } catch (Exception e) {
                // source 中未设置 get 方法的字段，会直接跳过
                // target 中没有 source 中的字段，也会直接跳过
            }
        }

        return target;
    }

    public static <S, T> List<T> copyListProperties_methodInvoke(List<S> sources, Supplier<T> targetSupplier) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T target = copyProperties_methodInvoke(source, targetSupplier);
            list.add(target);
        }
        return list;
    }

    /**************** 最佳实践 **************/

    public static <S, T> T copyProperties(S source, Supplier<T> targetSupplier) {
        if (source == null) {
            return null;
        }
        T target = targetSupplier.get();

        BeanUtils.copyProperties(source, target);

        return target;
    }

    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> targetSupplier) {
        if (CollectionUtils.isEmpty(sources)) {
            return Lists.newArrayList();
        }
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T target = copyProperties(source, targetSupplier);
            list.add(target);
        }
        return list;
    }

}