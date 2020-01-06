package jdk.proxy.jdk;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author nibnait
 * @version $Id: MyInvocationHandler.java, v 0.1 2019-09-02 下午7:05 nibnait Exp $$
 */
public class MyInvocationHandler implements InvocationHandler {

    /**
     * 目标对象
     */
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * JDK动态代理的核心
     * @param proxy  代理对象
     * @param method 目标类的真实方法
     * @param params 目标方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
        System.out.println("调用前 params: " + JSON.toJSONString(params));
        Object result = method.invoke(target, params);
        System.out.println("调用后 method: " + method.getName() + ", result: " + result);
        return result;
    }
}