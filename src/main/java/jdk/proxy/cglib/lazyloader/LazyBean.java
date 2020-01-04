package jdk.proxy.cglib.lazyloader;

import lombok.Data;
import lombok.ToString;
import net.sf.cglib.proxy.Enhancer;

/**
 * 说到延迟加载，应该经常接触到，尤其是使用Hibernate的时候，本篇将通过一个实例分析延迟加载的实现方式。
 * LazyLoader接口继承了Callback，因此也算是CGLib中的一种Callback类型。
 * <p>
 * 另一种延迟加载接口Dispatcher。
 * <p>
 * Dispatcher接口同样继承于Callback，也是一种回调类型。
 * <p>
 * 但是Dispatcher和LazyLoader的区别在于：LazyLoader只在第一次访问延迟加载属性时触发代理类回调方法，而Dispatcher在每次访问延迟加载属性时都会触发代理类回调方法。
 *
 * @author nibnait
 * @version $Id: LazyBean.java, v 0.1 2019-09-02 下午4:32 nibnait Exp $$
 */

@Data
@ToString
public class LazyBean {
    private String name;
    private int age;
    private PropertyBean propertyBean;
    private PropertyBean propertyBeanDispatcher;

    public LazyBean(String name, int age) {
        System.out.println("lazy bean init");
        this.name = name;
        this.age = age;
        this.propertyBean = createPropertyBean();
        this.propertyBeanDispatcher = createPropertyBeanDispatcher();
    }


    /**
     * 只第一次懒加载
     *
     * @return
     */
    private PropertyBean createPropertyBean() {
        /**
         * 使用cglib进行懒加载 对需要延迟加载的对象添加代理，在获取该对象属性时先通过代理类回调方法进行对象初始化。
         * 在不需要加载该对象时，只要不去获取该对象内属性，该对象就不会被初始化了（在CGLib的实现中只要去访问该对象内属性的getter方法，
         * 就会自动触发代理类回调）。
         */
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PropertyBean.class);
        PropertyBean pb = (PropertyBean) enhancer.create(PropertyBean.class, new ConcreteClassLazyLoader());
        return pb;
    }

    /**
     * 每次都懒加载
     *
     * @return
     */
    private PropertyBean createPropertyBeanDispatcher() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PropertyBean.class);
        PropertyBean pb = (PropertyBean) enhancer.create(PropertyBean.class, new ConcreteClassDispatcher());
        return pb;
    }
}