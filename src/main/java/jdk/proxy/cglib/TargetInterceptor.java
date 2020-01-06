package jdk.proxy.cglib;

import com.alibaba.fastjson.JSON;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 目标对象拦截器，实现MethodInterceptor
 *
 * @author nibnait
 * @version $Id: TargetInterceptor.java, v 0.1 2019-09-02 下午3:45 nibnait Exp $$
 */
public class TargetInterceptor implements MethodInterceptor {

    /**
     * 重写拦截方法，在方法前后加入业务
     *
     * @param obj    目标对象
     * @param method 目标方法
     * @param params 参数
     * @param proxy  CGlib方法代理对象
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] params, MethodProxy proxy) throws Throwable {
        System.out.println("调用前 params: " + JSON.toJSONString(params));
        Object result = proxy.invokeSuper(obj, params);
        System.out.println("调用后 method: " + method.getName() + ", result: " + result);
        return result;
    }


}

