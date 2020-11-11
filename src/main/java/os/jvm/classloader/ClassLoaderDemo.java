package os.jvm.classloader;

/**
 * Created by nibnait on 2020/11/11
 */
public class ClassLoaderDemo {

    public static void main(String[] args) {
        System.out.println("ClassLodarDemo 的类加载器 \t" + ClassLoaderDemo.class.getClassLoader());
        System.out.println("ClassLodarDemo 的父类的加载器 \t" + ClassLoaderDemo.class.getClassLoader().getParent());
        System.out.println("ClassLodarDemo 的爷爷类的加载器 \t" + ClassLoaderDemo.class.getClassLoader().getParent().getParent());
    }
}
