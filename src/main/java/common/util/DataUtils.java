package common.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.helpers.MessageFormatter;

import java.util.Collection;
import java.util.List;

/**
 * Created by nibnait on 2021/08/18
 */
@Slf4j
public class DataUtils {

    /**
     * 返回 list1 - (list1 ∩ list2)
     */
    public static <T> List<T> difference(Collection<T> list1, Collection<T> list2) {
        return Lists.newArrayList(Sets.difference(Sets.newHashSet(list1), Sets.newHashSet(list2)));
    }

    /**
     * 返回交集 list1 ∩ list2
     */
    public static <T> List<T> intersection(Collection<T> list1, Collection<T> list2) {
        return Lists.newArrayList(Sets.intersection(Sets.newHashSet(list1), Sets.newHashSet(list2)));
    }

    /**
     * @param format abc{}e
     * @param args   d
     * @return abcde
     */
    public static String format(String format, Object... args) {
        return MessageFormatter.arrayFormat(format, args).getMessage();
    }

    /**
     * @param clazz 类型不可为抽象类/接口
     */
    public static <T> T parseObject(String s, Class<T> clazz) {
        if (StringUtils.isBlank(s) || "null".equalsIgnoreCase(s)) {
            try {
                return clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                log.error(e.getMessage(), e);
                return null;
            }
        }
        try {
            return JSON.parseObject(s, clazz);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static <T> List<T> parseArray(String s, Class<T> clazz) {
        if (StringUtils.isBlank(s) || "null".equalsIgnoreCase(s)) {
            return Lists.newArrayList();
        }
        return JSON.parseArray(s, clazz);
    }

    public static String toJsonStringObject(Object o) {
        if (o == null) {
            return JSON.toJSONString(Maps.newHashMap());
        }
        return JSON.toJSONString(o);
    }

    public static String toJsonStringArray(Object o) {
        if (o == null) {
            return JSON.toJSONString(Lists.newArrayList());
        }
        return JSON.toJSONString(o);
    }
}
