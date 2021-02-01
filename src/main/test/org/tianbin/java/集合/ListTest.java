package org.tianbin.java.集合;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import common.util.CommonBeanUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*

Created by nibnait on 2020-01-09
 */
public class ListTest {

    @Test
    public void copyListProperties() {
        List<Integer> list = Lists.newArrayList(2,1,3);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }

    @Test
    public void testSubList() {
        List<Integer> list = Lists.newArrayList(1,2,3);
        System.out.println(list.subList(0,0));
    }

    @Test
    public void sort() {
        List<Integer> list = Lists.newArrayList(2,3,1);
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