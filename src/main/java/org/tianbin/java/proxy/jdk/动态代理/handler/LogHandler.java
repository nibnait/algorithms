package org.tianbin.java.proxy.jdk.动态代理.handler;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by nibnait on 2020/11/23
 */
public class LogHandler implements InvocationHandler {

    /**
     * 代理的对象
     */
    private Object target;

    public LogHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.doBefore();

        Object res = method.invoke(target, args);
        System.out.println("InvocationHandler invoke res: " + JSON.toJSONString(res));

        this.doAfter();

        return res;
    }

    private void doAfter() {
        System.out.println("log this after invoke");
    }

    private void doBefore() {
        System.out.println("log this before invoke");
    }
}
