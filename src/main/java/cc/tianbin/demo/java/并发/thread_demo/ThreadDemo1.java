package cc.tianbin.demo.java.并发.thread_demo;

import org.junit.Test;

/**
 * Created by nibnait on 2023/03/06
 */
public class ThreadDemo1 extends Thread {

    public static void main(String[] args) {
        ThreadDemo1 thread = new ThreadDemo1();

        thread.start();
    }

    @Override
    public void run() {
        System.out.println("hello world");
    }

    @Test
    public void test2() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        });

        thread.start();
    }

    @Test
    public void test3() {
        Thread thread = new Thread(() -> System.out.println("hello world"));
        thread.start();
    }
}
