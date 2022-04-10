package org.tianbin.java.集合;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import common.model.Person;
import io.github.nibnait.common.utils.DataUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListTest {

    @Test
    public void addAll() {
        List<Integer> list = new ArrayList<>();
        list.addAll(Lists.newArrayList(1,2,3));
        list.addAll(Lists.newArrayList());
        list.addAll(Lists.newArrayList(1));
        System.out.println(list);
    }

    @Test
    public void distinct() {
        List<Integer> list = Lists.newArrayList(1000113650, 1000115822, 1000112888, 1000119317, 1000113660, 1000116588, 1000112553, 1000112536, 1000112557, 1000112555, 1000112552, 1000110757, 1000112886, 1000112551, 1000112554, 1000107340, 1000110542, 1000107318, 1000107335, 1000107310, 1000107317, 1000107294, 1000107319, 1000107230, 1000098976, 1000107229, 1000100864);
        System.out.println(list.stream().distinct().collect(Collectors.toList()));
    }

    @Test
    public void remove2() {
//        List<String> list = Lists.newArrayList("1", "1", "2", "3", "1");
        List<String> list = Lists.newArrayList("1", "1");
        for (int i = 0; i < list.size(); i++) {
            if ("1".equals(list.get(i))) {
                list.remove(i);
                i = i - 1;
            } else {
                break;
            }
        }

        System.out.println(list);
    }

    @Test
    public void remove1() {
        List<Integer> list = Lists.newArrayList(1, 2, 3);
        for (Integer integer : list) {
            if (integer.equals(1)) {
                list.remove(integer);
            } else {
                break;
            }
        }

        System.out.println(list);
    }

    @Test
    public void listPointer() {
        List<Integer> children = Lists.newArrayList();
        Person person = new Person();
        person.setChildren(children);
        System.out.println(person.getChildren());

        children.add(1);
        System.out.println(person.getChildren());
    }

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
    public void duplicate() {
        List<Integer> list2 = Lists.newArrayList(1000059552,1000068557,1000052556,1000131519,1000131522,1000131511,1000108485,1000108487,1000108486,1000135252,1000135191,1000135205,1000135254,1000135294,1000135204,1000135251,1000135253,1000131523,1000103250,1000103249,1000107207,1000107237,1000102019,1000103724,1000103237,1000080830,1000121622,1000135781,1000092288,1000118620,1000117198,1000117197,1000117199,1000117202,1000117196,1000117201,1000117200,1000089820,1000095265,1000089823,1000089824,1000089822,1000099788,1000113684,1000108865,1000107903,1000107902,1000107901,1000103155,1000103156,1000105793,1000103170,1000095196,1000101180,1000142626,1000142627,1000142628,1000142622,1000142624,1000123426,1000082839,1000137961,1000098500,1000098502,1000098506,1000098507,1000098501,1000098512,1000098505,1000098504,1000098499,1000098509,1000098508,1000098514,1000098498,1000098533,1000098526,1000098532,1000098503,1000098510,1000096453,1000096450,1000096451,1000096475,1000096449,1000096452,1000096472,1000096448,1000096473,1000096474,1000096471,1000096476,1000114551,1000114552,1000125074,1000114574,1000114550,1000114575,1000114576,1000114549,1000114577,1000125103,1000081713,1000074323,1000081712,1000074893,1000066590,1000065049,1000066586,1000080773,1000096487,1000097000,1000079185,1000082541,1000079236,1000079242,1000065055,1000071376,1000086033,1000083975,1000088539,1000090970,1000108241,1000102089,1000144992,1000144996,1000137988,1000137989,1000137987,1000136264,1000136265,1000134619,1000135895,1000135896,1000144785,1000144786,1000140793,1000020449,1000055809,1000063560,1000073840,1000069340,1000074104,1000069284,1000081510,1000136063,1000136073,1000136068,1000136069,1000136067,1000136074,1000136071,1000108686,1000143503,1000143506,1000143504,1000143507,1000143505,1000143509,1000143508,1000126707,1000137585,1000144843,1000144850,1000143728,1000144848,1000143435,1000143440,1000143726,1000143765,1000141521,1000143776,1000058101,1000144838,1000144829,1000143775,1000143437,1000143451,1000143774,1000144833,1000143773,1000110453,1000115892,1000116547,1000135991,1000135992,1000135986,1000135989,1000135994,1000135990,1000135988,1000135993,1000135995,1000135996,1000135987,1000135997,1000135998,1000144734,1000144735,1000143079,1000077184,1000077173,1000081673,1000103442,1000082640,1000082642,1000082641,1000080636,1000080635,1000080640,1000080641,1000080637,1000080633,1000080642,1000080634,1000080643,1000080644,1000080632,1000080645,1000080639,1000082804,1000124142,1000124210,1000125452,1000125451,1000125819,1000126215,1000125448,1000125817,1000125820,1000125822,1000125821,1000125449,1000125818,1000126216,1000125456,1000125462,1000121975,1000121978,1000121976,1000121990,1000121977,1000121992,1000122033,1000126230,1000122032,1000121991,1000121974,1000130142,1000130141,1000130208,1000130217,1000130209,1000130140,1000130147,1000130120,1000130119,1000130149,1000130121,1000130210,1000137765,1000137767,1000137766,1000104969,1000104968,1000104966,1000104967,1000044389,1000117614,1000141714,1000141748,1000141712,1000117414,1000109711,1000125796,1000125800,1000125797,1000125794,1000125795,1000125798,1000125799,1000125768,1000125769,1000125773,1000125786,1000125767,1000125787,1000125772,1000125771,1000125770,1000125785,1000125784,1000125789,1000125790,1000125788,1000143411,1000143407,1000143410,1000143415,1000143416,1000143405,1000145378,1000073625,1000093298,1000093254,1000100205,1000093252,1000093296,1000100204,1000093253,1000093297,1000093251,1000093295,1000093255,1000093294,1000121128,1000121129,1000121130,1000121145,1000121148,1000121147,1000121132,1000121143,1000121142,1000121131,1000121146,1000121133,1000121144,1000121126,1000121127,1000121141,1000138891,1000136215,1000136214,1000080657,1000046345,1000073221,1000080304,1000080303,1000083084,1000137834,1000050231,1000099816,1000099850,1000099856,1000099853,1000118303,1000099851,1000099814,1000099817,1000099815,1000099859,1000099813,1000099852,1000117372,1000099858,1000099857,1000050229,1000083925,1000088374,1000118409,1000124465,1000135679,1000113224,1000135965,1000117379,1000117382,1000076376,1000097159,1000069772,1000135682,1000020598,1000012142,1000145302,1000135763,1000135761,1000146633,1000146628,1000146632,1000146629,1000146630,1000146631,1000090610,1000145652,1000145651,1000145650,1000145649,1000102854,1000122089,1000122090,1000122091,1000122092,1000117617,1000105993,1000099789,1000109229,1000130918,1000139271,1000139270,1000101700,1000064426,1000135315,1000108684,1000126887,1000131448,1000135849,1000146553,1000039831,1000088833,1000094936,1000131158,1000131167,1000130400,1000131150,1000130401,1000131154,1000135622,1000137613,1000137619,1000134634,1000130405,1000111163,1000111164,1000114457,1000111161,1000114459,1000114460,1000111162,1000114458,1000136072,1000136062,1000112962,1000112963,1000113047,1000113046,1000113049,1000112972,1000112971,1000112970,1000112974,1000113034,1000113031,1000112976,1000116329,1000113027,1000132090,1000139391,1000139401,1000139384,1000118973,1000085962,1000085963,1000100970,1000100825,1000107335,1000107340,1000112551,1000110757,1000112552,1000112555,1000116588,1000113660,1000113650,1000139230,1000139229,1000103628,1000119317,1000135709,1000135706,1000135708,1000136027,1000136028,1000136029,1000136025,1000136026,1000136030,1000135705,1000135707,1000136059,1000136060,1000136061,1000134840,1000134843,1000136058,1000134839,1000136050,1000134841,1000134844,1000134845,1000136047,1000136048,1000136051,1000136049,1000136056,1000136057);

        List<Integer> duplicateElements = DataUtils.getDuplicateElements(list2);
        System.out.println(duplicateElements);

        System.out.println(Sets.newHashSet(list2));
    }

    @Test
    public void difference() {
//        List<Integer> all = Lists.newArrayList(1, 2, 3, 3);
//        List<Integer> sub = all.stream().distinct().collect(Collectors.toList());
//
//        List<Integer> difference = DataUtils.difference(all, sub);
//        System.out.println(difference);
//        List<Integer> expect = Lists.newArrayList(3);
//        Assert.assertTrue(CompareUtils.match(difference, expect));


        List<Integer> list1 = Lists.newArrayList(1000082236, 1000134040, 1000134041, 1000073245, 1000134042, 1000134036, 1000135700, 1000134037, 1000134039, 1000135703, 1000135404, 1000135400, 1000135401, 1000135402, 1000135403, 1000135460, 1000135456, 1000070182, 1000135457, 1000135459);
        List<Integer> list2 = Lists.newArrayList(1000134040, 1000134041, 1000073245, 1000134042, 1000134036, 1000135700, 1000134037, 1000134039, 1000135703, 1000082236, 1000135460, 1000135456, 1000070182, 1000135457, 1000135459, 1000135404, 1000135400, 1000135401, 1000135402, 1000135403);
        List<Integer> difference1 = DataUtils.difference(list1, list2);
        System.out.println(difference1);


        List<Integer> intersection = DataUtils.intersection(list1, list2);
        System.out.println(intersection);

        List<Integer> difference = DataUtils.difference(list2, intersection);
//        System.out.println(difference);
//        difference1 = DataUtils.difference(list1, list2);
//        System.out.println(difference1);
    }

    @Test
    public void intersection() {
        List<Integer> oldList = Lists.newArrayList(0);
        List<Integer> newList = Lists.newArrayList(1, 3);
        List<Integer> difference1 = DataUtils.intersection(newList, oldList);
        System.out.println(difference1);
        difference1 = DataUtils.intersection(oldList, newList);
        System.out.println(difference1);

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
        List<Integer> list = Lists.newArrayList();
        for (int i = 1; i < 33; i++) {
            list.add(i);
        }

        int pageNum = 1;
        int pageSize = 10;
        int fromIndex = pageNum * pageSize;
        int toIndex = fromIndex + pageSize;
        System.out.println(list.subList(fromIndex, toIndex));

        pageNum = 2;
        fromIndex = pageNum * pageSize;
        toIndex = fromIndex + pageSize;
        System.out.println(list.subList(fromIndex, toIndex));

        pageNum = 3;
        fromIndex = pageNum * pageSize;
        toIndex = fromIndex + pageSize;
        System.out.println(list.subList(fromIndex, toIndex));

        pageNum = 4;
        fromIndex = pageNum * pageSize;
        toIndex = fromIndex + pageSize;
        System.out.println(list.subList(fromIndex, toIndex));
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