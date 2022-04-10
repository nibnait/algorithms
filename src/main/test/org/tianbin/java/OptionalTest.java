package org.tianbin.java;

import org.junit.Test;

import java.util.Optional;

/**
 * Created by nibnait on 2022/03/17
 */
public class OptionalTest {

    @Test
    public void test01() {
        Integer i = null;
        System.out.println(Optional.ofNullable(i).orElse(null));

        i = 1;
        System.out.println(Optional.ofNullable(i).orElse(null));
    }
}
