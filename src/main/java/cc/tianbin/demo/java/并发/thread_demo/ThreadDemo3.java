package cc.tianbin.demo.java.并发.thread_demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by nibnait on 2023/03/06
 */
public class ThreadDemo3 implements Callable<String> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new ThreadDemo3());
        Thread thread = new Thread(futureTask);
        thread.start();
        String result = futureTask.get();
        System.out.println(result);
    }

    @Override
    public String call() throws Exception {
        return "hello world";
    }
}
