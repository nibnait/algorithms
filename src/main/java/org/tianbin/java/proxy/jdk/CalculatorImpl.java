package org.tianbin.java.proxy.jdk;

/**
 * Created by nibnait on 2020/11/23
 */
public class CalculatorImpl implements Calculator {
    @Override
    public int add(int x, int y) {
        return x + y;
    }
}
