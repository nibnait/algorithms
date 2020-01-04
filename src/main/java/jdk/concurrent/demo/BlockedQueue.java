package jdk.concurrent.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 管程
 *
 * @author nibnait
 * @version $Id: BlockedQueue.java, v 0.1 2019-08-31 下午1:50 nibnait Exp $$
 */
public class BlockedQueue<T>{
    final Lock lock = new ReentrantLock();
    // 条件变量:队列不满
    final Condition notFull = lock.newCondition();
    // 条件变量:队列不空
    final Condition notEmpty = lock.newCondition();

    //入队
    void enq(T x) {
        lock.lock();
        try {
            while (true) { //队列已满
                // 等待队列不满
                notFull.await();
                // 省略入队操作...

                // 入队后, 通知可出队
                notEmpty.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //出队
    void deq(){
        lock.lock();
        try {
            while (true) { //队列已空
                // 等待队列不空
                notEmpty.await();
                // 省略出队操作...

                // 出队后，通知可入队
                notFull.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
