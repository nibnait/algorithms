package org.tianbin.java.基本数据类型;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by nibnait on 2020/11/11
 */
public class BigDecimalTest {

    @Test
    public void test04() {
        int i = 1;
        BigDecimal bigDecimal1 = new BigDecimal(i);
        System.out.println(bigDecimal1);

        double d = 1.83;
        BigDecimal bigDecimal2 = new BigDecimal(d);
        System.out.println(bigDecimal2);

        BigDecimal divide = bigDecimal2.divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP);
        System.out.println(divide);
    }

    @Test
    public void test03() {
        Double d1 = 1.0;
        System.out.println("d1 = " + BigDecimal.valueOf(d1).stripTrailingZeros());
        Double d2 = 1.00;
        System.out.println("d2 = " + BigDecimal.valueOf(d2).stripTrailingZeros());
        Double d3 = 1.000;
        System.out.println("d3 = " + BigDecimal.valueOf(d3).stripTrailingZeros());
        Double d4 = 1.001;
        System.out.println("d4 = " + BigDecimal.valueOf(d4).stripTrailingZeros());
        Double d5 = 1.01;
        System.out.println("d5 = " + BigDecimal.valueOf(d5).stripTrailingZeros());
        Double d6 = 1.11;
        System.out.println("d6 = " + BigDecimal.valueOf(d6).stripTrailingZeros());
    }

    @Test
    public void test02() {
        System.out.println(BigDecimal.valueOf(getInt()));
    }

    private Integer getInt() {
        return null;
    }

    @Test
    public void test01() {

        double value = 100.2;

        BigDecimal bigDecimal = new BigDecimal(value);
        BigDecimal bigDecimal1 = BigDecimal.valueOf(value);

        System.out.println("new " + bigDecimal);
        System.out.println("valueOf " + bigDecimal1);
    }
}
