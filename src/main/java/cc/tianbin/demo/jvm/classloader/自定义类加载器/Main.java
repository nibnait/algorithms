package cc.tianbin.demo.jvm.classloader.自定义类加载器;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created by nibnait on 2020/11/11
 */
public class Main {

    /**
     * 如果第一次运行 使用的是AppClassLoader，说明idea帮我自动编译后，把class文件放到项目的CLASSPATH路径下。
     * 当运行 testLoadDemo() 时，当然要先让起父加载器（AppClassLoader）来加载。正确。
     * <p>
     * 解决方案：
     * 法1. 把target目录下 classes/os/jvm/classloader/自定义类加载器 里面的LoadDemo.class 删除，
     * 这样 AppClassLoader 没找到 LoadDemo.class，自然就会加载失败，然后让他的儿子【自定义加载器】来加载器
     *
     * 法2. 在初始化 MyClassLoader()时，将其父加载器设置为扩展类加载器，
     * 扩展类加载器 加载的是 $JAVA_HOME/lib/ext/*.jar中的class文件，里面肯定没有LoadDemo.class。。
     */
    @Test
    public void testLoadDemo() throws Exception {
//        MyClassLoader classLoader = new MyClassLoader();
        MyClassLoader classLoader = new MyClassLoader(ClassLoader.getSystemClassLoader().getParent());
        classLoader.setRoot("/Users/nibnait/github/algorithms/src/main/java/org/tianbin/jvm/classloader/自定义类加载器");

        Class<?> clz = Class.forName("cc.tianbin.jvm.classloader.自定义类加载器.LoadDemo", true, classLoader);
        Object instance = clz.newInstance();
        Method test = clz.getDeclaredMethod("test");
        test.setAccessible(true);
        test.invoke(instance);

        System.out.println(instance.getClass().getClassLoader());
    }

    /**
     * 使用自定义类加载器 加载手写java.lang.String时，在加载-验证阶段，就被jvm的安全机制拦截了【Prohibited package name】
     * @throws Exception
     */
    @Test
    public void testLoadString() throws Exception {
        MyClassLoader classLoader = new MyClassLoader();
        classLoader.setRoot("/Users/nibnait/github/algorithms/src/main/test/java/lang");

        Class<?> clz = classLoader.findClass("java.lang.String");
        Object instance = clz.newInstance();
        try {
            Method test = clz.getDeclaredMethod("test");
            test.setAccessible(true);
            test.invoke(instance);
        } catch (Exception e) {}

        System.out.println(instance.getClass().getClassLoader());
    }
}
