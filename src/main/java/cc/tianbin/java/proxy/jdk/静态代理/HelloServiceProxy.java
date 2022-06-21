package cc.tianbin.java.proxy.jdk.静态代理;

import cc.tianbin.java.proxy.jdk.HelloService;

/**
 * Created by nibnait on 2020/11/23
 */
public class HelloServiceProxy implements HelloService {
    private HelloService helloService;

    public HelloServiceProxy(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public void sayHello() {
        helloService.sayHello();
    }

    @Override
    public void speak(String word) {
        helloService.speak(word);
    }
}
