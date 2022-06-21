package cc.tianbin.java.并发.JUC.JUC_AQS;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by nibnait on 2020/11/4
 */
public class CountdownLatchExample {

    /**
     * 所有线程自己执行自己的，每个线程 只要调用一次 countDownLatch.countDown(); 计数器就减一
     * 走到 countDownLatch.await(); 时，快的线程会等待慢的线程，执行完。
     * 再继续往下走。
     *
     * 或者 当 countDown减到0时，即使慢的线程还没执行完，也会继续往下走。。不等了。。。
     *
     * @throws InterruptedException
     */
    @Test
    public void testCase() throws InterruptedException {
        int totalThread = 90;
        CountDownLatch countDownLatch = new CountDownLatch(totalThread);

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 101; i++) {
            int finalI = i;
            executor.execute(() -> {
                System.out.println("run.." + finalI);
                countDownLatch.countDown();

                System.out.println("after.." + finalI);
            });
        }

        countDownLatch.await();

        System.out.println("...一起落库...");
        executor.shutdown();
    }

}
