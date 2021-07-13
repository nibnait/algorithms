package org.tianbin.java;

import org.junit.Test;

/**
 * Created by nibnait on 2021/07/13
 */
public class SwitchCaseTest {

    @Test
    public void test01() {

//        Integer i = 1;
        Integer i = null;

        switch (i) {
            case 1:
                System.out.println("1:" + i);
//                break;
            case 2:
                System.out.println("2:" + i);
//                break;
            default:
                System.out.println("default:" + i);
        }

    }

}
