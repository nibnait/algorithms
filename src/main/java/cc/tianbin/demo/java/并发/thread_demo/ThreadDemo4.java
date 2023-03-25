package cc.tianbin.demo.java.并发.thread_demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by nibnait on 2023/03/06
 */
public class ThreadDemo4 implements Runnable {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new ThreadDemo4());

        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new ThreadDemo4());
    }

    @Override
    public void run() {
        System.out.println("hello world");
    }
}
