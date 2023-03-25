package cc.tianbin.demo.java.并发.threadpool;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by nibnait on 2023/03/14
 */
public class Main {


    @Test
    public void ThreadPoolExecutor() {
        Executors.newSingleThreadExecutor();
        Executors.newFixedThreadPool(1);


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory(),
                // ThreadPoolExecutor.defaultHandler
                new ThreadPoolExecutor.AbortPolicy());



    }

}
