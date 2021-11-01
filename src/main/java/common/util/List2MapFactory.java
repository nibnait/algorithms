package common.util;

import com.google.common.collect.Maps;
import common.exception.ClientViewException;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.helpers.MessageFormatter;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class List2MapFactory {

    public static final String DOUBLE_INDEX_FORMAT = "{}_{}";
    public static final String TRIPLE_INDEX_FORMAT = "{}_{}_{}";

    public class BizMap <K, V> {

        private Map<K, V> map;

        public BizMap(Map<K, V> map) {
            this.map = map;
        }

        public BizMap() {
            this.map = Maps.newHashMap();
        }

        public V get(K key, String format, Object... args) {
            V v = map.get(key);
            if (v == null) {
                throw new ClientViewException(DataUtils.format(format, args));
            }
            return v;
        }

        public V get(K key) {
            return map.get(key);
        }

        public Optional<V> getOptional(K key) {
            return Optional.ofNullable(map.get(key));
        }

        public Map<K, V> getMap() {
            return map;
        }
    }


    public <T, R> BizMap<R, T> transferMap(List<T> list, Function<T, R> function) {
        if (CollectionUtils.isEmpty(list)) {
            return new BizMap<>();
        }
        return new BizMap<>(list.stream().collect(Collectors.toMap(function, Function.identity(), (o1, o2) -> o1)));
    }

    public <T, R> BizMap<R, List<T>> transferMapGroupBy(List<T> list, Function<T, R> function) {
        if (CollectionUtils.isEmpty(list)) {
            return new BizMap<>();
        }
        return new BizMap<>(list.stream().collect(Collectors.groupingBy(function)));
    }

    public <T, R1, R2> BizMap<String, T> transferDoubleIndexMap(List<T> list, Function<T, R1> fun1, Function<T, R2> fun2) {
        if (CollectionUtils.isEmpty(list)) {
            return new BizMap<>();
        }
        return new BizMap<>(list.stream().collect(Collectors.toMap(o -> doubleIndexFormat(fun1.apply(o), fun2.apply(o)), Function.identity(), (o1, o2) -> o1)));
    }

    public String doubleIndexFormat(Object... args) {
        return MessageFormatter.arrayFormat(DOUBLE_INDEX_FORMAT, args).getMessage();
    }

    public <T, R1, R2, R3> BizMap<String, T> transferTripleIndexMap(List<T> list, Function<T, R1> fun1, Function<T, R2> fun2, Function<T, R3> fun3) {
        if (CollectionUtils.isEmpty(list)) {
            return new BizMap<>();
        }
        return new BizMap<>(list.stream().collect(Collectors.toMap(o -> tripleIndexFormat(fun1.apply(o), fun2.apply(o), fun3.apply(o)), Function.identity(), (o1, o2) -> o1)));
    }

    public String tripleIndexFormat(Object... args) {
        return MessageFormatter.arrayFormat(TRIPLE_INDEX_FORMAT, args).getMessage();
    }

}
