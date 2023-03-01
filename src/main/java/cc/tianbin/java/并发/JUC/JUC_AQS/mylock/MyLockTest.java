package cc.tianbin.java.并发.JUC.JUC_AQS.mylock;

import common.util.LogUtil;

import static cc.tianbin.java.并发.JUC.JUC_AQS.mylock.MyLock.MY_LOCK_NAME;

/**
 * Created by nibnait on 2023/02/01
 */
public class MyLockTest {

    private static MyLock lock = new MyLock();

    /**
     * 自定义一把锁
     * 设计一把自定义的锁你高要多久？需求如下：
     * 1.排它锁，只有线程 name 以"myLock”开头的才能获取剑
     * 2.不支持重入，喜欢一次藏够了。
     * 3.支持尝试加锁
     * 4,支持超时尝试加锁
     * 5.支持可中衙加锁
     * 6.支wait notify功能
     * 7.其他不以“myLock”开头的线程名称，一律拒之门外。
     */
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            testLock();
        });

        Thread t2 = new Thread(() -> {
            testLock();
        });

        t1.setName(MY_LOCK_NAME);
//        t1.setName("xx" + MY_LOCK_NAME);
        t2.setName("xxx");

        t1.start();
        t2.start();

    }

    private static void testLock() {
        lock.lock();

        try {
            LogUtil.log("{} 拿到了锁", Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }


}
