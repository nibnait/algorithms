package zzzTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import com.sun.tools.corba.se.idl.constExpr.Or;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;
import utils.JsonHelper;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
//    @AllArgsConstructor
//    static class Apple {
//        int age;
//
//        @Override
//        public String toString() {
//            return "Apple{" +
//                    "age=" + age +
//                    '}';
//        }
//    }

    @AllArgsConstructor
    @Getter
    @Data
    @NoArgsConstructor
    public static class OrderTip {
        /** 用于recordTip */
        private String id;
        private OrderTipType type;

        /** 模块 */
        private String title;
        private String description;
        private String href;
        private Switch_Type switchType;
        private String imageUrl;
        private Boolean test01;
        private Boolean test02;
    }

    public static void main(String[] args) throws JsonProcessingException {
        List<String> phones = Lists.newArrayList("123");
        String phone = null;
        int index = phones.indexOf(phone) + 1;
        System.out.println(index);
    }

    private static void history() throws JsonProcessingException {
        //        String str2 = "[1500006324,1583984232,43212343,23423421]";
//        String str1 = "orderOperationRecord:order:3123213213123123213";
//        String str = "{\"orderId\":3123213213123123213,\"shopId\":3123213213123123213,\"orderOperation\":\"CONFIRM\",\"operationEnvironment\":{\"platform\":\"OPEN_API\",\"deviceType\":null,\"deviceId\":null,\"confirmDescription\":null},\"time\":\"2018-10-09T19:35:24.446\"}";
//        System.out.println(str1.length());

//        List<String> strings = Lists.newArrayList("1", "2");
//        System.out.println(String.join(",", strings));

//        OrderTip orderTip = new OrderTip("1",OrderTipType.WECHAT, "微信接单小助手", "建议开启", "/app/we-chat", Switch_Type.NATIVE_PROMPT_TONE, "imageUrl");
//        setsadfaf(orderTip);
//        System.out.println(JsonHelper.toJsonString(orderTip));
//
//
//        String appVersion = "8.10.7";
//        String huskarVersion = "8.0.0";
//        String v2 = "7.8";
//        String v3 = "7.9.8";
//        String v4 = "8.9.8";
//        String[] huskarVersionSplit = huskarVersion.split("\\.");
//        String[] appVersionSplit = appVersion.split("\\.");
//        for (int i = 0; i < appVersionSplit.length; i++) {
//            if (Integer.valueOf(huskarVersionSplit[i]) > Integer.valueOf(appVersionSplit[i])) {
//                System.out.println("大于");
//            }
//        }
    }

    private static void setsadfaf(OrderTip orderTip) {
        orderTip.setId("2");
    }

    private static <R> String trimBracket(String str) {
        if (str.contains("[")) {
            return str.substring(1, str.length());
        }
        if (str.contains("]")) {
            return str.substring(0, str.length()-1);
        }
        return str;
    }

    private static void setAAA(A a) {
        a.setName(2);
    }

    public static long toLong(Object value, String name) {
        Long valueLong = toLongObject(value, name);
        return valueLong.longValue();
    }

    public static Long toLongObject(Object value, String name) {
        if (value == null)
            return null;
        try {
            return Long.valueOf(toString(value));
        } catch (Exception e) {
        }
        return null;
    }

    public static String toString(Object value) {
        return value == null ? null : String.valueOf(value);
    }

    private static List<Integer> getlist() {
        return Lists.newArrayList(0);
    }

    @AllArgsConstructor
    @Data
    static class A {
        int name;
    }
    private static List<Integer> get() {
        return Lists.newArrayList(1);
    }
}

