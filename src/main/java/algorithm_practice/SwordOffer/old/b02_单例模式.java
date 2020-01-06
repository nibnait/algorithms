package algorithm_practice.SwordOffer.old;


/**
 * Created by nibnait on 2016/9/20.
 */
public class b02_单例模式 {}

//1. 饿汉式
class Singleton_EHan{
    //类加载时就初始化
    private static final Singleton_EHan INSTANCE = new Singleton_EHan();
    private Singleton_EHan(){};  //保证不被其他类new出来
    public static Singleton_EHan getInstance(){
        return INSTANCE;
    }
}

//2. 懒汉式
class Singleton_LanHan{
    private static Singleton_LanHan INSTANCE;
    private Singleton_LanHan(){};

/*  懒汉式线程不安全的
    public static Singleton_LanHan getInstance(){
        if (INSTANCE == null){
            return new Singleton_LanHan();
        }
        return INSTANCE;
    }
*/
    //懒汉式线程安全的：
    public static synchronized Singleton_LanHan getInstance(){
        if (INSTANCE == null){
            return new Singleton_LanHan();
        }
        return INSTANCE;
    }
}

//3. 双重检验锁模式
class Singleton_DoubleCheck{

    private volatile static Singleton_DoubleCheck INSTANCE; //volatile可以使instance变量不会在多线程中存在副本，直接从内存中读
            //即：volatile的赋值操作后面会有个“内存屏障”，防止读操作被JVM重排序到内存屏障之前。
    private Singleton_DoubleCheck(){}

    public static Singleton_DoubleCheck getInstance(){
        if (INSTANCE == null){
            synchronized (Singleton_DoubleCheck.class){
                if (INSTANCE == null){
                    return new Singleton_DoubleCheck();
                }
            }
        }
        return INSTANCE;
    }

}

//4. 静态内部类
class Singleton_StaticInnerClass {
    private static class SingletonHolder{
        private static final Singleton_StaticInnerClass INSTANCE = new Singleton_StaticInnerClass();
    }
    private Singleton_StaticInnerClass(){}
    public static Singleton_StaticInnerClass getInstance(){
        return SingletonHolder.INSTANCE;    //只有在调用 getInstance() 时，对象才会被创建，同时没有性能缺点，也不依赖 interview.Java 版本。
    }
}

//5. 枚举
enum Singleton {
    INSTANCE;
    public static Singleton getInstance(){
        return INSTANCE;
    }
}