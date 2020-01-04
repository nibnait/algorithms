package jdk.concurrent.demo.threadpool;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static common.util.SystemUtil.sleep;

/**
 * 任务之间有聚合关系：CompletableFuture
 *
 * T3需要等待T1 和 T2 执行完，在开始执行
 *
 * Created by nibnait on 2019-09-01
 */
public class b2_CompletableFutureDemo extends TestCase {

    /**
     * T1：洗水壶（1min） --> 烧开水（15min）
     * T2：洗茶壶（1min） --> 洗茶杯（2min） --> 拿茶叶（1min）
     * <p>
     * T3：泡茶
     */
    @Test
    public void testMain() {
        //任务1:洗水壶->烧开水
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(
                () -> {
                    System.out.println("T1:洗水壶...");
                    sleep(1, TimeUnit.SECONDS);
                    System.out.println("T1:烧开水...");
                    sleep(15, TimeUnit.SECONDS);
                });

        //任务2:洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("T2:洗茶壶...");
                    sleep(1, TimeUnit.SECONDS);
                    System.out.println("T2:洗茶杯...");
                    sleep(2, TimeUnit.SECONDS);
                    System.out.println("T2:拿茶叶...");
                    sleep(1, TimeUnit.SECONDS);
                    return "⻰井";
                });

        //任务3:任务1和任务2完成后执行:泡茶
        CompletableFuture<String> f3 = f1.thenCombine(
                f2, (__, tf) -> {
                    System.out.println("T1:拿到茶叶:" + tf);
                    System.out.println("T1:泡茶...");
                    return "上茶：" + tf;
                });

        //等待任务3执行结果
        System.out.println(f3.join());

    }

}
