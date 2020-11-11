package org.tianbin.java.jdk_源码阅读_练习.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.*;

/**
 * Created by nibnait on 2019-08-27
 */
public class Main {

    public static void main(String[] args) {
        AbstractQueuedSynchronizer abstractQueuedSynchronizer;
        ReentrantLock reentrantLock;
        Condition condition;

        ReentrantReadWriteLock reentrantReadWriteLock;
        StampedLock stampedLock;
        Semaphore semaphore;

        CountDownLatch countDownLatch;
        CyclicBarrier cyclicBarrier;

        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;


        //线程池
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(10);
        ThreadPoolExecutor.AbortPolicy handler = new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, 2,
                0L, TimeUnit.MILLISECONDS,
                workQueue, handler);


        FutureTask futureTask;
        CompletableFuture completableFuture;



    }
}
