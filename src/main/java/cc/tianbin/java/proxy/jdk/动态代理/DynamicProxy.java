package cc.tianbin.java.proxy.jdk.动态代理;

import cc.tianbin.java.proxy.jdk.动态代理.handler.LogHandler;
import org.junit.Test;
import cc.tianbin.java.proxy.jdk.Calculator;
import cc.tianbin.java.proxy.jdk.CalculatorImpl;

import java.lang.reflect.Proxy;

/**
 * Created by nibnait on 2020/11/23
 */
public class DynamicProxy {

    @Test
    public void testCase() {
        CalculatorImpl calculator = new CalculatorImpl();

        Calculator proxy = (Calculator) Proxy.newProxyInstance(
                Calculator.class.getClassLoader(),
                new Class[]{Calculator.class},
                new LogHandler(new CalculatorImpl())
        );

        int sum = proxy.add(1, 2);
        System.out.println("\nproxy excute res: " + sum);
    }

}
