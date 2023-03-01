package cc.tianbin.demo.java.并发.threadpool;

import java.util.concurrent.*;

/**
 * Created by nibnait on 2020/11/19
 */
public class ExecutorsExceptionTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(5,
                10,
                30, TimeUnit.SECONDS,
                new ArrayBlockingQueue(1000),
                new ThreadPoolExecutor.CallerRunsPolicy());

        executorService.execute(() -> sayHi("execute"));
        executorService.execute(() -> sayHi("execute"));
        executorService.execute(() -> sayHi("execute-exception"));
        executorService.execute(() -> sayHi("execute"));
        executorService.execute(() -> sayHi("execute"));
        Thread.sleep(2000);
        System.out.println("\n============== submit: ==================");


        Future<?> future = executorService.submit(() -> sayHi("submit"));
        try {
            future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void sayHi(String name) {
        String printStr = "【thread-name:" + Thread.currentThread().getName() + ",执行方式:" + name+"】";

        if ("execute-exception".equals(name)) {
            throw new RuntimeException(printStr + ",我异常啦!哈哈哈!");
        } else {
            System.out.println(printStr);
        }
    }

}
