package jdk.concurrent.悲观锁vs乐观锁.reentrantLock.线程间的协作;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    /**
     * 虽然 b 线程先启动，
     * 但是因为在 b 线程中调用了 a 线程的 join() 方法，b 线程会等待 a 线程结束才继续执行，
     * 因此最后能够保证 a 线程的输出先于 b 线程的输出。
     */
    @Test
    public void testJoinExample() {
        JoinExample.A a = new JoinExample.A();
        JoinExample.B b = new JoinExample.B(a);
        b.start();
        a.start();
    }

    /**
     *
     */
    @Test
    public void testWaitNotifyExample() {
        WaitNotifyExample example = new WaitNotifyExample();

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(() -> example.runWait());
        executor.execute(() -> example.runNotify());
//        executor.execute(() -> example.runNotifyAll());
    }

    /**
     * notify() 和 notifyAll() 只能唤醒自己对象对应的相关线程
     */
    @Test
    public void testWaitNotifyExample2() {
        WaitNotifyExample example = new WaitNotifyExample();
        WaitNotifyExample example2 = new WaitNotifyExample();

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(() -> example.runWait());
        executor.execute(() -> example2.runNotifyAll());
    }

    @Test
    public void testAwaitSignalExample() {
        AwaitSignalExample example = new AwaitSignalExample();

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(() -> example.runAwait());
//        executor.execute(() -> example.runSignal());
        executor.execute(() -> example.runSignalAll());
    }

    @Test
    public void testAwaitAutoSignal() {
        AwaitSignalExample example = new AwaitSignalExample();

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(() -> example.runAwaitAutoSignal());
        executor.execute(() -> example.runSignalAll());
    }

}
