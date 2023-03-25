package cc.tianbin.demo.java.继承;

/**
 * Created by nibnait on 2023/03/22
 */
public class HelloA {

    static {
        System.out.println("static a");
    }

    {
        System.out.println("I'm a class");
    }

    public HelloA() {
        System.out.println("hello a");
    }

}
