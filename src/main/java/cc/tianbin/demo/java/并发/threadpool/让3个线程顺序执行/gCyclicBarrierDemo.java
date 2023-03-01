package cc.tianbin.demo.java.并发.threadpool.让3个线程顺序执行;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by nibnait on 2020/11/27
 */
public class gCyclicBarrierDemo {


    @Test
    public void testCase() {
        CyclicBarrier c1 = new CyclicBarrier(2);
        CyclicBarrier c2 = new CyclicBarrier(2);

        final Thread thread1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("1. 把冰箱门打开");

                c1.await();
            }
        });

        final Thread thread2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                c1.await();
                System.out.println("2. 把大象放进去");

                c2.await();
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                c2.await();
                System.out.println("3. 把冰箱门关上");
            }
        });

        System.out.println("thread3.start");
        thread3.start();
        System.out.println("thread2.start");
        thread2.start();
        System.out.println("thread1.start");
        thread1.start();

    }

}
