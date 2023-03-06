package cc.tianbin.demo.java.并发.threadpool.让3个线程顺序执行;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单线程池
 * Created by nibnait on 2020/11/27
 */
public class eSingleThreadPoolDemo {

    @Test
    public void testCase() {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Thread thread1 = new Thread(() -> System.out.println("1. 把冰箱门打开"));
        Thread thread2 = new Thread(() -> System.out.println("2. 把大象放进去"));
        Thread thread3 = new Thread(() -> System.out.println("3. 把冰箱门关上"));

        System.out.println("thread1.start");
        executor.submit(thread1);
        System.out.println("thread2.start");
        executor.submit(thread2);
        System.out.println("thread3.start");
        executor.submit(thread3);

        executor.shutdown();
    }

}
