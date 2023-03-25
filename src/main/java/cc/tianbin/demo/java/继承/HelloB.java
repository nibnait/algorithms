package cc.tianbin.demo.java.继承;

/**
 * Created by nibnait on 2023/03/22
 */
public class HelloB extends HelloA {

    static {
        System.out.println("static b");
    }

    {
        System.out.println("I'm b class");
    }

    public HelloB() {
        System.out.println("hello b");
    }

    public static void main(String[] args) {
        System.out.println("start");

        new HelloB();

        System.out.println("end");
    }
}
