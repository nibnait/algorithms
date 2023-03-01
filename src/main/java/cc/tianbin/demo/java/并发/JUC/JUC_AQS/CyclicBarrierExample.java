package cc.tianbin.demo.java.并发.JUC.JUC_AQS;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by nibnait on 2020/11/4
 */
public class CyclicBarrierExample {

    /**
     * cyclicBarrier.await() 之后，线程进入主动等待状态。
     * 直到 cyclicBarrier计数器归零。
     *
     * 主线程 和 线程池中的所有线程继续并发执行
     */
    @Test
    public void testCase() {
        int totalThread = 100;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(totalThread);

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < totalThread; i++) {
            int finalI = i;
            executor.execute(() -> {
                System.out.print("run.." + finalI + "  ");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }

                System.out.println("after.." + finalI);
            });
        }

        System.out.println("...一起落库...");

        executor.shutdown();
    }

}
