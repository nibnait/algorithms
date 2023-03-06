package cc.tianbin.demo.java.并发.threadpool;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static cc.tianbin.common.util.SystemUtil.sleep;

/**
 * 任务之间有聚合关系：CompletableFuture
 *
 * T3需要等待T1 和 T2 执行完，在开始执行
 *
 * Created by nibnait on 2019-09-01
 */
public class b2_CompletableFutureDemo2 extends TestCase {

    /**
     * T1：洗水壶（1min） --> 烧开水（15min）
     * T2：洗茶壶（1min） --> 洗茶杯（2min） --> 拿茶叶（1min）
     * <p>
     * T3：泡茶
     */
    @Test
    public void testMain() {
        //任务1:洗水壶->烧开水
//        CompletableFuture<Void> f1 = CompletableFuture.runAsync(
//                () -> {
//                    System.out.println("T1:洗水壶...");
//                    sleep(1, TimeUnit.SECONDS);
//                    System.out.println("T1:烧开水...");
//                    sleep(15, TimeUnit.SECONDS);
//                });

        //任务2:洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("T2:洗茶壶...");
                    sleep(1, TimeUnit.SECONDS);
                    System.out.println("T2:洗茶杯...");
                    sleep(2, TimeUnit.SECONDS);
                    System.out.println("T2:拿茶叶...");
                    sleep(20, TimeUnit.SECONDS);
                    return "⻰井";
                });

        String taskId = "123";
        Integer time = 10;
        TimeUnit timeUnit = TimeUnit.MINUTES;

        try {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(() -> getF1Return(f1, taskId, time, timeUnit));

        } catch (Exception e) {

        }


        System.out.println("end");
        sleep(10, TimeUnit.MINUTES);
    }

    private String getF1Return(CompletableFuture<String> f1, String taskId, Integer time, TimeUnit timeUnit) {
        try {
            String s = f1.get();
            System.out.println(s + " ... " + taskId);
        } catch (Exception e) {

        }
        return taskId;
    }

}
