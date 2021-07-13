package org.tianbin.java.基本数据类型;

import org.junit.Test;

/**
 * Created by nibnait on 2021/03/18
 */
public class IntegerTest {

    @Test
    public void testCompareTo() {
        Integer old = 110;
        Integer newI = 11110;
        System.out.println(newI - old);

        old = 122222;
        newI = 2222;
        System.out.println(newI - old);

        old = 77777;
        newI = 77777;
        System.out.println(newI - old);
    }

    @Test
    public void test02() {
        int i1 = 166;
        System.out.println(i1 / 100);
        int i2 = 133;
        System.out.println(i2 / 100);
    }

    @Test
    public void test01() {
        String s = "xxx";
        int i = Integer.parseInt(s);
        System.out.println(i);
    }

}
