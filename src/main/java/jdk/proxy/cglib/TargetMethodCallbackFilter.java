package jdk.proxy.cglib;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * 回调方法过滤
 *
 * @author nibnait
 * @version $Id: TargetMethodCallbackFilter.java, v 0.1 2019-09-02 下午4:21 nibnait Exp $$
 */
public class TargetMethodCallbackFilter implements CallbackFilter {

    /**
     * 过滤方法
     * 返回的值为数字，代表了Callback数组中的索引位置，要到用的Callback
     */
    @Override
    public int accept(Method method) {
        if(method.getName().equals("eat")){
            System.out.println("filter eat ==0");
            return 0;
        }

        if(method.getName().equals("walk")){
            System.out.println("filter walk ==1");
            return 1;
        }

        if(method.getName().equals("sleep")){
            System.out.println("filter sleep ==2");
            return 2;
        }
        return 0;
    }

}