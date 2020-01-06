package jdk.concurrent.demo.threadpool;

import common.util.SystemUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 管理批量并行任务：CompletionService
 *
 * Created by nibnait on 2019-09-01
 */
public class b3_CompletionServiceDemo extends TestCase {

    @Test
    public void testMain() {
        System.out.println(getResult());
    }

    private Integer getResult() {
        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // 创建CompletionService
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        // 用于保存Future对象
        List<Future<Integer>> futures = new ArrayList<>(3);
        //提交异步任务，并保存future到futures
        futures.add(cs.submit(() -> geocoderByS1()));
        futures.add(cs.submit(() -> geocoderByS2()));
        futures.add(cs.submit(() -> geocoderByS3()));

        // 获取最快返回的任务执行结果
        Integer r = 0;
        try {
            // 只要有一个成功返回，则break
            for (int i = 0; i < 3; ++i) {
                r = cs.take().get();
                //简单地通过判空来检查是否成功返回
                if (r != null) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //取消所有任务
            for (Future<Integer> f : futures) {
                f.cancel(true);
            }
        }
        // 返回结果
        return r;
    }

    private Integer geocoderByS1() {
        SystemUtil.sleep(3, TimeUnit.SECONDS);
        return 1;
    }

    private Integer geocoderByS2() {
        SystemUtil.sleep(2, TimeUnit.SECONDS);
        return 2;
    }

    private Integer geocoderByS3() {
        SystemUtil.sleep(1, TimeUnit.SECONDS);
        return 3;
    }

}
