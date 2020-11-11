package org.tianbin.java.jdk_源码阅读_练习.proxy.cglib.lazyloader;

import org.tianbin.java.jdk_源码阅读_练习.proxy.cglib.Person;
import net.sf.cglib.proxy.Dispatcher;

/**
 * @author nibnait
 * @version $Id: ConcreteClassDispatcher.java, v 0.1 2019-09-02 下午4:36 nibnait Exp $$
 */
public class ConcreteClassDispatcher implements Dispatcher {

    @Override
    public Object loadObject() throws Exception {
        System.out.println("before Dispatcher...");
        PropertyBean propertyBean = new PropertyBean();
        propertyBean.setKey("xxx");
        propertyBean.setValue(new Person());
        System.out.println("after Dispatcher...");
        return propertyBean;
    }

}

