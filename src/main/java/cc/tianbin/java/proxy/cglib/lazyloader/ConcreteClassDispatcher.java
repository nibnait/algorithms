package cc.tianbin.java.proxy.cglib.lazyloader;

import common.model.Person;
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

