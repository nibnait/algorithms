package cc.tianbin.demo.java.并发.threadpool.让3个线程顺序执行;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 ReentrantLock.Condition 的 await 方法
 * Created by nibnait on 2020/11/27
 */
public class dThreadAwaitSingalDemo {

    /**
     * Condition（条件变量）:通常与一个锁关联。需要在多个Condition中共享一个锁时，可以传递一个Lock/RLock实例给构造方法，否则它将自己生成一个RLock实例。
     *
     * Condition中await()方法类似于Object类中的wait()方法。
     *
     * Condition中await(long time,TimeUnit unit)方法类似于Object类中的wait(long time)方法。
     *
     * Condition中signal()方法类似于Object类中的notify()方法。
     *
     * Condition中signalAll()方法类似于Object类中的notifyAll()方法。
     *
     * 应用场景：Condition是一个多线程间协调通信的工具类，使得某个，或者某些线程一起等待某个条件（Condition）,只有当该条件具备( signal 或者 signalAll方法被调用)时 ，这些等待线程才会被唤醒，从而重新争夺锁。
     */

    private static boolean t1Run = false;
    private static boolean t2Run = false;

    @Test
    public void testCase() {

        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();

                System.out.println("1. 把冰箱门打开");
                t1Run = true;
                condition1.signal();

                lock.unlock();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                lock.lock();

                if (!t1Run) {
                    System.out.println("thread2 先于thread1抢到锁了，condition1.await thread2进入等待状态");
                    condition1.await();
                }

                System.out.println("2. 把大象放进去");
                t2Run = true;
                condition2.signal();

                lock.unlock();
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                lock.lock();

                if (!t2Run) {
                    System.out.println("thread3 先于thread2抢到锁了，condition2.await thread3进入等待状态");
                    condition2.await();
                }

                System.out.println("3. 把冰箱门关上");

                lock.unlock();
            }
        });

        System.out.println("thread3.start");
        thread3.start();
        System.out.println("thread2.start");
        thread2.start();
        System.out.println("thread1.start");
        thread1.start();
    }

}
