package org.tianbin.java.jdk_源码阅读_练习.concurrent.悲观锁vs乐观锁.reentrantLock.线程间的协作;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitSignalExample {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void runSignal() {
        lock.lock();
        try {
            System.out.println("signal");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void runSignalAll() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.lock();
        try {
            System.out.println("signalAll");
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void runAwait() {
        lock.lock();
        try {
            condition.await();
            System.out.println("await");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 5秒后 自己醒来
     */
    public void runAwaitAutoSignal() {
        lock.lock();
        try {
            System.out.println("await");
            if (condition.await(5, TimeUnit.SECONDS)) {
                System.out.println("被其他线程唤醒");
            } else {
                System.out.println("自己醒了");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
