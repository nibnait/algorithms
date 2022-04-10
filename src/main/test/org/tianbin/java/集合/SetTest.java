package org.tianbin.java.集合;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*

Created by nibnait on 2020-01-14
 */
public class SetTest {

    @Test
    public void test01() {
        ArrayList list1 = Lists.newArrayList(1, 2, 3);
        ArrayList list2 = Lists.newArrayList(2, 3, 4);

        System.out.println(Lists.newArrayList(Sets.difference(Sets.newHashSet(list1), Sets.newHashSet(list2))));
        System.out.println(Lists.newArrayList(Sets.intersection(Sets.newHashSet(list1), Sets.newHashSet(list2))));
    }

    @Test
    public void testCase() {
        System.out.println(isRestaurantInGray(1l, ","));
    }

    private static boolean isRestaurantInGray(long restaurantId, String grayRestaurantIdStr) {

        if (StringUtils.isBlank(grayRestaurantIdStr)) {
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