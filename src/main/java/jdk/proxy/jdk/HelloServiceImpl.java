package jdk.proxy.jdk;

/**
 * @author nibnait
 * @version $Id: HelloServiceImpl.java, v 0.1 2019-09-02 下午7:04 nibnait Exp $$
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("Hello world!");
    }
}