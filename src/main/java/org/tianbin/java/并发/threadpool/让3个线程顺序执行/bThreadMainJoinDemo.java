package org.tianbin.java.并发.threadpool.让3个线程顺序执行;

import org.junit.Test;

/**
 * 使用主线程的join方法
 * Created by nibnait on 2020/11/27
 */
public class bThreadMainJoinDemo {

    @Test
    public void testCase() throws InterruptedException {

        Thread thread1 = new Thread(() -> System.out.println("1. 把冰箱门打开"));
        Thread thread2 = new Thread(() -> System.out.println("2. 把大象放进去"));
        Thread thread3 = new Thread(() -> System.out.println("3. 把冰箱门关上"));

        System.out.println("thread1.start");
        thread1.start();
        thread1.join();

        System.out.println("thread2.start");
        thread2.start();
        thread2.join();

        System.out.println("thread3.start");
        thread3.start();
        thread3.join();

    }

}
