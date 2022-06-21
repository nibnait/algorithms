package cc.tianbin.java.并发.threadpool.让3个线程顺序执行;

import org.junit.Test;

/**
 * 通过子程序join使线程按顺序执行
 * Created by nibnait on 2020/11/27
 */
public class aThreadJoinDemo {

    /**
     * join():是Theard的方法，作用是调用线程需等待该join()线程执行完成后，才能继续用下运行。
     *
     * 应用场景：当一个线程必须等待另一个线程执行完毕才能执行时可以使用join方法。
     */
    @Test
    public void testCase() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1. 把冰箱门打开");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread1.join();
                    System.out.println("2. 把大象放进去");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread2.join();
                    System.out.println("3. 把冰箱门关上");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("thread1.start");
        thread1.start();
        System.out.println("thread2.start");
        thread2.start();
        System.out.println("thread3.start");
        thread3.start();
    }

}
