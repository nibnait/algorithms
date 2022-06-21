package cc.tianbin.java.并发.threadpool.让3个线程顺序执行;

import lombok.SneakyThrows;
import org.junit.Test;

/**
 * 使用Object 的 wait方法
 * Created by nibnait on 2020/11/27
 */
public class cThreadWaitNotifyDemo {

    /**
     * wait():是Object的方法，作用是让当前线程进入等待状态，同时，wait()也会让当前线程释放它所持有的锁。“直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法”，当前线程被唤醒(进入“就绪状态”)
     * <p>
     * notify()和notifyAll():是Object的方法，作用则是唤醒当前对象上的等待线程；notify()是唤醒单个线程，而notifyAll()是唤醒所有的线程。
     * <p>
     * wait(long timeout):让当前线程处于“等待(阻塞)状态”，“直到其他线程调用此对象的notify()方法或 notifyAll() 方法，或者超过指定的时间量”，当前线程被唤醒(进入“就绪状态”)。
     */

    private static boolean t1Run = false;
    private static boolean t2Run = false;

    @Test
    public void testCase() {

        Object object1 = new Object();
        Object object2 = new Object();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object1) {
                    System.out.println("1. 把冰箱门打开");
                    t1Run = true;
                    object1.notify();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (object1) {

                    if (!t1Run) {
                        System.out.println("thread2 先于thread1抢到锁了，object1.wait thread2进入等待状态");
                        object1.wait();
                    }

                    synchronized (object2) {
                        System.out.println("2. 把大象放进去");
                        t2Run = true;
                        object2.notify();
                    }
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (object2) {
                    if (!t2Run) {
                        System.out.println("thread3 先于thread2抢到锁了，object2.wait thread3进入等待状态");
                        object2.wait();
                    }

                    System.out.println("3. 把冰箱门关上");
                }
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
