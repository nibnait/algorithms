package jdk.proxy.cglib;

import junit.framework.TestCase;
import net.sf.cglib.proxy.*;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * TestCglib
 * 生成动态代理类
 *
 * @author nibnait
 * @version $Id: TestCglib.java, v 0.1 2019-09-02 下午4:16 nibnait Exp $$
 */
public class Main extends TestCase {

    /**
     * 生成动态代理类
     */
    @Test
    public void testEnhancer() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);
        enhancer.setCallback(new TargetInterceptor());

        Person person = (Person) enhancer.create();
        System.out.println("person response: " + person + "\n");
        System.out.println("person.eat() response: " + person.eat("饭") + "\n");
        System.out.println("person.walk() response: " + person.walk(100) + "\n");
        System.out.println("person.sleep() response: " + person.sleep(200) + "\n");
    }

    /**
     * 回调过滤器 CallbackFilter
     */
    @Test
    public void testCallbackFilter() {

        /**
         * 1. callback：方法拦截器
         * 2. NoOp.INSTANCE：这个NoOp表示no operator，即什么操作也不做，代理类直接调用被代理的方法不进行拦截。
         * 3. FixedValue：表示锁定方法返回值，无论被代理类的方法返回什么值，回调方法都返回固定值。
         */
        Callback callback = new TargetInterceptor();
        Callback noopCb = NoOp.INSTANCE;
        Callback fixedValue = new TargetResultFixed();
        Callback[] callbacks = new Callback[]{callback, noopCb, fixedValue};

        /**
         * filter eat ==0   callback 使用拦截器
         * filter walk ==1  noopCb 啥也不做
         * filter sleep ==2 fixedValue 锁定方法返回值
         */
        CallbackFilter callbackFilter = new TargetMethodCallbackFilter();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(callbackFilter);

        Person person = (Person) enhancer.create();
        System.out.println("person response: " + person + "\n");
        System.out.println("person.eat() response: " + person.eat("饭") + "\n");
        System.out.println("person.walk() response: " + person.walk(100) + "\n");
        System.out.println("person.sleep() response: " + person.sleep(200) + "\n");
    }

    /**
     * 接口生成器 InterfaceMaker
     *
     * @param args
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        InterfaceMaker interfaceMaker = new InterfaceMaker();

        //抽取某个类的方法生成接口方法
        interfaceMaker.add(Person.class);
        Class<?> targetInterface = interfaceMaker.create();
        for (Method method : targetInterface.getMethods()) {
            System.out.println(method.getName());
        }
        System.out.println("================================");

        //接口代理并设置代理接口方法拦截
        Object object = Enhancer.create(Object.class, new Class[]{targetInterface}, new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                if (method.getName().equals("eat")) {
                    System.out.println("filter eat ");
                    return "吃了";
                }
                if (method.getName().equals("walk")) {
                    System.out.println("filter walk ");
                    return 1024;
                }
                if (method.getName().equals("sleep")) {
                    System.out.println("filter sleep ");
                    return 8;
                }
                return "default";
            }
        });

        Method sleep = object.getClass().getMethod("sleep", new Class[]{int.class});
        int sleepResult = (int) sleep.invoke(object, new Object[]{33});
        System.out.println("sleepResult: " + sleepResult + "\n");

        Method eat = object.getClass().getMethod("eat", new Class[]{String.class});
        Object eatResult = eat.invoke(object, new Object[]{"屎"});
        System.out.println("eatResult: " + eatResult + "\n");
    }
}