package jdk.concurrent.互斥同步.reentrantLock;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    @Test
    public void testCase1() {
        LockExample lockExample = new LockExample();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {lockExample.func();});
        executorService.execute(() -> {lockExample.func();});
    }

    @Test
    public void testCase2() {
        LockExample lockExample = new LockExample();
        LockExample lockExample2 = new LockExample();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {lockExample.func();});
        executorService.execute(() -> {lockExample2.func();});
    }

}
