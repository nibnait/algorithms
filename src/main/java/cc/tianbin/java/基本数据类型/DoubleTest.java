package cc.tianbin.java.基本数据类型;

import org.junit.Test;

/**
 * Created by nibnait on 2021/06/02
 */
public class DoubleTest {

    @Test
    public void test01() {
        String s = "19.9";
        Double d = Double.valueOf(s);
        System.out.println(d);
    }

    @Test
    public void test02() {
        double d = 19.9;

        double v = d * 100;
        System.out.println(v);

        int i = (int)v;
        System.out.println(i);

        int d1 = 1989;
        double d2 = (double) d1 / 100;
        System.out.println(d2);

        d1 = 1990;
        d2 = (double) d1 / 100;
        System.out.println(d2);

    }

    @Test
    public void test03() {
        double d = 19.9;

        double v = d * 0.5;
        System.out.println(v);

        int i = (int)v;
        System.out.println(i);

    }

}
