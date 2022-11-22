package cc.tianbin.java.并发;

import org.junit.Test;

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

    @Test
    public void testReentrantLook() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.tryLock();
        lock.tryLock(0, null);


        // 公平锁
        ReentrantLock fairSync = new ReentrantLock(true);
        // 非公平锁
        ReentrantLock nonfairSync = new ReentrantLock(false);
        // 指的是lock方法，发生抢占那一刻，是直接去后面排队，还是先跟当前排到的人 cas 一下

    }
}
