package jdk.concurrent;

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

        FutureTask futureTask;
        CompletableFuture completableFuture;
    }
}
