package org.tianbin.java.反射.compare;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nibnait on 2020/11/11
 */
public class Main {

    @Test
    public void test01() {
        Order left = getLeftValue();

        Order right = getRightValue();

        Map<String, String> errorMap = new HashMap<>();
        left.compareTo(right, errorMap);

        System.out.println(JSON.toJSONString(errorMap));
    }

    private Order getRightValue() {
        Order order = new Order(2, "456");
        Map<String, String> detailJson = new HashMap<>();


        order.setDetailJson(detailJson);
        return order;
    }

    private Order getLeftValue() {
        Order order = new Order(1, "123");
        Map<String, String> detailJson = new HashMap<>();


        order.setDetailJson(detailJson);
        return order;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Order {
        private long orderId;
        private String phone;

        private Map<String, String> detailJson;

        public Order(long orderId, String phone) {
            this.orderId = orderId;
            this.phone = phone;
        }

        public boolean compareTo(Object that, Map<String, String> errorMap) {
            if (that == null) {
                errorMap.put("比对", "当前右值为空");
            }

            if (!(that instanceof Order)) {
                errorMap.put("比对", "右值类型不对");
            }

            // 校验 本次比对是否跳过
            // 初始化白名单
            // 初始化黑名单

            CompareUtil.compare(this, that, errorMap);

            return errorMap.size() == 0;
        }
    }


}
