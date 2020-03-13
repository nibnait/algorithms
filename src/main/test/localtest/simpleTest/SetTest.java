package localtest.simpleTest;

import common.util.StringUtil;
import junit.framework.TestCase;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*

Created by nibnait on 2020-01-14
 */
public class SetTest extends TestCase {

    @Test
    public void testCase() {
        System.out.println(isRestaurantInGray(1l, ","));
    }

    private static boolean isRestaurantInGray(long restaurantId, String grayRestaurantIdStr) {

        if (StringUtil.isBlank(grayRestaurantIdStr)) {
            return false;
        }

        Set<String> grayValues = Stream.of(grayRestaurantIdStr.split(","))
                .map(String::trim)
                .collect(Collectors.toSet());

        if (CollectionUtils.isNotEmpty(grayValues) && grayValues.contains("-1")) {
            return true;
        }

        return grayValues.contains(String.valueOf(restaurantId));
    }
}