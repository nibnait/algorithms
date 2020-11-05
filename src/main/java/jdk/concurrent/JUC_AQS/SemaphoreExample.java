package jdk.concurrent.JUC_AQS;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 限流器
 * Created by nibnait on 2020/11/4
 */
public class SemaphoreExample {

    /**
     * 以下代码模拟了对某个服务的并发请求，每次只能有 3 个客户端同时访问，请求总数为 10。
     *
     * semaphore.acquire(); 会将信号量里的计数器减 1，是一个原子操作。
     *
     * 当信号量的计数器 >= 0，线程可以继续执行
     * 当信号量的计数器 < 0，线程进入阻塞状态，等待其他线程 释放信号量semaphore.release(); 编程可执行状态
     */
    @Test
    public void testCase() {
        int clintCount = 3;
        int totalRequestCount = 100;

        Semaphore semaphore = new Semaphore(clintCount);
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < totalRequestCount; i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    System.out.println(finalI + "..start");

                    semaphore.acquire();
                    System.out.println(finalI + "..继续执行，availablePermits:" + semaphore.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

            });
        }

        executor.shutdown();
    }

}
