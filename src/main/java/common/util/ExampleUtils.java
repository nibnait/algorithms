package common.util;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.function.UnaryOperator;

/**
 * Created by nibnait on 2021/10/10
 */
public class ExampleUtils {

    public static<T> void addQueryCriteria(T criteria, Object object, UnaryOperator<T> unaryOperator) {
        if (object == null) {
            return;
        }
        if (List.class.isAssignableFrom(object.getClass()) && CollectionUtils.isEmpty((List) object)) {
            return;
        }
        unaryOperator.apply(criteria);
    }

}
