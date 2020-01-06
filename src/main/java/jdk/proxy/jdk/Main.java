package jdk.proxy.jdk;

import junit.framework.TestCase;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * 使用JDK动态代理的五大步骤:
 * 1.通过实现InvocationHandler接口来自定义自己的InvocationHandler;
 * 2.通过Proxy.getProxyClass获得动态代理类
 * 3.通过反射机制获得代理类的构造方法，方法签名为getConstructor(InvocationHandler.class)
 * 4.通过构造函数获得代理对象并将自定义的InvocationHandler实例对象传为参数传入
 * 5.通过代理对象调用目标方法
 *
 * @author nibnait
 * @version $Id: Main.java, v 0.1 2019-09-02 下午7:07 nibnait Exp $$
 */
public class Main extends TestCase {

    @Test
    public void testMain() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // =========================第一种==========================
        // 1、生成$Proxy0的class文件
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 2、获取动态代理类
        Class proxyClazz = Proxy.getProxyClass(HelloService.class.getClassLoader(), HelloService.class);
        // 3、获得代理类的构造函数，并传入参数类型InvocationHandler.class
        Constructor constructor = proxyClazz.getConstructor(InvocationHandler.class);
        // 4、通过构造函数来创建动态代理对象，将自定义的InvocationHandler实例传入
        HelloService Tom = (HelloService) constructor.newInstance(new MyInvocationHandler(new HelloServiceImpl()));
        // 5、通过代理对象调用目标方法
        Tom.sayHello();

        // ==========================第二种=============================
        /**
         * Proxy类中还有个将2~4步骤封装好的简便方法来创建动态代理对象，
         *其方法签名为：newProxyInstance(ClassLoader loader,Class<?>[] instance, InvocationHandler h)
         */
        HelloService Jerry = (HelloService) Proxy.newProxyInstance(HelloService.class.getClassLoader(), // 加载接口的类加载器
                new Class[]{HelloService.class}, // 一组接口
                new MyInvocationHandler(new HelloServiceImpl())); // 自定义的InvocationHandler
        Jerry.sayHello();

    }
}