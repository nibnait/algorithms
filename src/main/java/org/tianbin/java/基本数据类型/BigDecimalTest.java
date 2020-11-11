package org.tianbin.java.基本数据类型;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by nibnait on 2020/11/11
 */
public class BigDecimalTest {

    @Test
    public void testCase() {

        double value = 100.2;

        BigDecimal bigDecimal = new BigDecimal(value);
        BigDecimal bigDecimal1 = BigDecimal.valueOf(value);

        System.out.println(bigDecimal);
        System.out.println(bigDecimal1);
    }
}
