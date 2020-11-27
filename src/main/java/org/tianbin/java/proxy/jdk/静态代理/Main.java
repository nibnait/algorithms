package org.tianbin.java.proxy.jdk.静态代理;

import org.junit.Test;
import org.tianbin.java.proxy.jdk.HelloServiceImpl;

/**
 * Created by nibnait on 2020/11/23
 */
public class Main {

    @Test
    public void testCase() {
        HelloServiceProxy helloServiceProxy = new HelloServiceProxy(new HelloServiceImpl());

        helloServiceProxy.sayHello();
    }

}
