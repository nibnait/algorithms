package cc.tianbin.demo.java.并发.悲观锁vs乐观锁.reentrantLock;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

    public static void main(String[] args) {
        ReentrantLock reentrantLock;

        ReentrantReadWriteLock reentrantReadWriteLock;
    }

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
