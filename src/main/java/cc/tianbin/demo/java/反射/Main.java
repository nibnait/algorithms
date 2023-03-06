package cc.tianbin.demo.java.反射;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import cc.tianbin.common.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nibnait on 2020/11/11
 */
public class Main {

    @Test
    public void test01() {
        Order left = getLeftValue();
        Order right = getRightValue();

        Map<String, String> errorMap = CompareUtil.compare(left, right, Lists.newArrayList("testByte"));

        System.out.println(JSON.toJSONString(errorMap));
    }

    private Order getLeftValue() {
        Order order = new Order();
        order.setOrderId(0L);
        order.setTestInteger(0);
        order.setTestLong(0L);
        order.setTestByte((byte)0);
        order.setTestDouble(0.0D);
        order.setTestFloat(1.0F);
        order.setPrice(BigDecimal.valueOf(0));
        order.setPhone("");

        Map<String, String> detailJson = new HashMap<>();
        detailJson.put("1", "1");
        order.setDetailJson(detailJson);

        ArrayList<Person> personList = Lists.newArrayList();
        Person person = new Person();
        person.setName("");
        person.setAge(0);
        person.setGender((byte)0);
        person.setPhone("");
        person.setIsBigBoolean(false);
        person.setSmallBoolean(false);
        personList.add(person);
        order.setPersonList(personList);

        order.setIsBigBoolean(true);
        order.setSmallBoolean(true);
        return order;
    }

    private Order getRightValue() {
        Order order = new Order();
        order.setOrderId(1L);
        order.setTestInteger(1);
        order.setTestLong(1L);
        order.setTestByte((byte)1);
        order.setTestDouble(0.1D);
        order.setTestFloat(1.0F);
        order.setPrice(BigDecimal.valueOf(1));
        order.setPhone("");

        Map<String, String> detailJson = new HashMap<>();
        detailJson.put("1", "2");
        order.setDetailJson(detailJson);

        ArrayList<Person> personList = Lists.newArrayList();
        Person person = new Person();
        person.setName("");
        person.setAge(0);
        person.setGender((byte)0);
        person.setPhone("");
        person.setIsBigBoolean(true);
        person.setSmallBoolean(true);
        personList.add(person);
        order.setPersonList(personList);

        order.setIsBigBoolean(false);
        order.setSmallBoolean(false);
        return order;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Order {
        private long orderId;
        private Integer testInteger;
        private Long testLong;
        private Byte testByte;
        private Double testDouble;
        private Float testFloat;
        private BigDecimal price;
        private String phone;
        private Map<String, String> detailJson;
        private List<Person> personList;
        private Boolean isBigBoolean;
        private boolean isSmallBoolean;

    }


}
