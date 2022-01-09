package org.tianbin.java.集合;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import common.model.Person;
import common.util.DataUtils;
import common.util.compare.CompareUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListTest {

    @Test
    public void sum() {
        List<Person> list = Lists.newArrayList();
        int sum = list.stream().mapToInt(Person::getAge).sum();
        System.out.println(sum);
    }

    @Test
    public void sorted() {
        List<Person> people = new ArrayList<>();
        Person p1 = new Person();
        p1.setAge(1);
        people.add(p1);

        Person p2 = new Person();
        p2.setAge(2);
        people.add(p2);

        Person p3 = new Person();
        p3.setAge(3);
        people.add(p3);

        people = people.stream().sorted(Comparator.comparing(Person::getAge).reversed()).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(people));

        System.out.println("===========");
        System.out.println(JSON.toJSONString(people));
        people.sort((o1, o2) -> {
            return o1.getAge() - o2.getAge();
        });
        System.out.println(JSON.toJSONString(people));
    }

    @Test
    public void difference() {
        List<Integer> all = Lists.newArrayList(1, 2, 3, 3);
        List<Integer> sub = all.stream().distinct().collect(Collectors.toList());

        List<Integer> difference = DataUtils.difference(all, sub);
        List<Integer> expect = Lists.newArrayList(3);
        Assert.assertTrue(CompareUtils.match(difference, expect));
    }

    @Test
    public void testContains() {
        Integer i = null;
        List<Integer> list = Lists.newArrayList(1);
        System.out.println(list.contains(i));

        System.out.println(list.contains(null));
    }

    @Test
    public void test03() {
        List<String> strList = Lists.newArrayList("1", "2");
        System.out.println(strList.stream().collect(Collectors.joining(",")));
    }

    @Test
    public void test02() {
        List<Integer> list = Lists.newArrayList();
        for (Integer integer : list) {
            System.out.println(integer.intValue());
        }
    }

    @Test
    public void copyListProperties() {
        List<Integer> list = Lists.newArrayList(2, 1, 3);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }

    @Test
    public void testSubList() {
        List<Integer> list = Lists.newArrayList(1, 2, 3);
        System.out.println(list.subList(0, 0));
    }

    @Test
    public void sort() {
        List<Integer> list = Lists.newArrayList(2293, 2304, 2313, 2408, 2419, 2421, 2066, 2273, 2278, 2312, 2418, 2274, 2331, 2273);
        Collections.sort(list);
        System.out.println(list);
    }


    private static final String DEFAULT_CLOSE_VALUE = "0";
    private static final String DEFAULT_OPEN_VALUE = "-1";

    @Test
    public void testCase01() {
        System.out.println(inGray("[0]", "1"));
        System.out.println(inGray("[-1]", "1"));
        System.out.println(inGray("[1]", "1"));
    }

    public static boolean inGray(String configValue, String cityId) {
        List<String> configList = new ArrayList<>();
        try {
            configList = JSONObject.parseObject(configValue, ArrayList.class);
            configList = Lists.transform(configList, Functions.toStringFunction());
        } catch (Exception e) {
//            LoggerUtil.error(e, log, "HuskarUtil.inGray error, configValue:%s", configValue);
        }
        if (configList.contains(DEFAULT_CLOSE_VALUE)) {
            return false;
        }
        if (configList.contains(DEFAULT_OPEN_VALUE)) {
            return true;
        }
        return configList.contains(cityId);
    }


}