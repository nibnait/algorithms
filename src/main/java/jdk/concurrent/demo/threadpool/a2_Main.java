package jdk.concurrent.demo.threadpool;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by nibnait on 2019-09-01
 */
public class a2_Main extends TestCase {



    @Test
    public void testFutureTask() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 1+2);
        Thread thread = new Thread(futureTask);
        thread.start();
        Integer result = futureTask.get();
        System.out.println(result);
    }
}
