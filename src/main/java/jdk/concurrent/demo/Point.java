package jdk.concurrent.demo;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock
 * 写锁、悲观读锁、乐观读
 *
 * @author nibnait
 * @version $Id: Point.java, v 0.1 2019-08-31 下午9:46 nibnait Exp $$
 */
class Point {
    private double x, y;
    final StampedLock stampedLock = new StampedLock();

    /**
     * 一种完全锁定方法
     *
     * @param deltaX
     * @param deltaY
     */
    void move(double deltaX, double deltaY) {
        long stamp = stampedLock.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    /**
     * 乐观读 计算到原点的距离
     * @return
     */
    double distanceFromOrigin() {
        // 乐观读
        long stamp = stampedLock.tryOptimisticRead();
        // 读入局部变量
        // 读的过程数据可能被修改
        double curX = x, curY = y;
        // 判断执行读操作期间，是否存在写操作
        // 如果存在，则sl.validate false
        if (!stampedLock.validate(stamp)) {
            // 升级为悲观读锁
            stamp = stampedLock.readLock();
            try {
                curX = x;
                curY = y;
            } finally {
                // 释放悲观读锁
                stampedLock.unlockRead(stamp);
            }
        }
        return Math.sqrt(curX * curX + curY * curY);
    }

    /**
     * 使用 StampedLock 一定不要调用中断操作，
     * 如果需要支持中断功能，一定使用可中断的 悲观读锁 readLockInterruptibly() 和写锁 writeLockInterruptibly()。
     *
     * @throws InterruptedException
     */
    void interrupt() throws InterruptedException {
        final StampedLock lock
                = new StampedLock();
        Thread T1 = new Thread(() -> {
            // 获取写锁
            lock.writeLock();
            // 永远阻塞在此处，不释放写锁
            LockSupport.park();
        });
        T1.start();
        // 保证T1 获取写锁
        Thread.sleep(100);

        Thread T2 = new Thread(() ->
                // 阻塞在悲观读锁
                lock.readLock()
        );
        T2.start();
        // 保证T2 阻塞在读锁
        Thread.sleep(100);
        // 终端线程T2
        // 会导致T2 所在CPU 飙升。。内部实现里while循环里面对中断的处理有点问题
        T2.interrupt();
        T2.join();
    }

    /**
     * 锁升级
     */
    void moveIfAtOrigin(double newX, double newY) {
        long stamp = stampedLock.readLock();
        try {
            while (x == 0.0 && y == 0.0) {
                long ws = stampedLock.tryConvertToWriteLock(stamp);
                if (ws != 0L) {
                    // 更新邮戳，否则无法释放写锁
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                } else {
                    stampedLock.unlockRead(stamp);
                    stamp = stampedLock.writeLock();
                }
            }
        } finally {
            stampedLock.unlock(stamp);
        }
    }
}