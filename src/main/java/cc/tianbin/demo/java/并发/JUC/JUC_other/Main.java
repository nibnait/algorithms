package cc.tianbin.demo.java.并发.JUC.JUC_other;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by nibnait on 2020/11/5
 */
public class Main {
    
    @Test
    public void testReadWriteLock() {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        Lock readLock = readWriteLock.readLock();

        Lock writeLock = readWriteLock.writeLock();
    }

    /**
     * ForkJoin 使用 ForkJoinPool 来启动，它是一个特殊的线程池，线程数量取决于 CPU 核数。
     */
    @Test
    public void testForkJoinExample() throws ExecutionException, InterruptedException {
        ForkJoinExample example = new ForkJoinExample(1, 1000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        Future future = forkJoinPool.submit(example);
        System.out.println(future.get());
    }

    @Test
    public void testProducerConsumerExample() {
        for (int i = 0; i < 2; i++) {
            ProducerConsumer.Producer producer = new ProducerConsumer.Producer();
            producer.start();
        }
        for (int i = 0; i < 5; i++) {
            ProducerConsumer.Consumer consumer = new ProducerConsumer.Consumer();
            consumer.start();
        }
        for (int i = 0; i < 3; i++) {
            ProducerConsumer.Producer producer = new ProducerConsumer.Producer();
            producer.start();
        }
    }

}
