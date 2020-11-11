package org.tianbin.java.jdk_源码阅读_练习.concurrent.悲观锁vs乐观锁.synchronize;

public class SynchronizedExample {

    /**
     * 只锁 本对象的 func1方法
     */
    public void func1() {
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.print(i + " ");
            }
            System.out.println("\nover");
        }
    }

    /**
     * 只锁 本对象的 func1方法
     */
    public synchronized void func2() {
        for (int i = 0; i < 100; i++) {
            System.out.print(i + " ");
        }
        System.out.println("\nover");
    }

    /**
     * 锁 SynchronizedExample.class 这个类的所有对象的 func3
     */
    public synchronized static void func3() {
        for (int i = 0; i < 100; i++) {
            System.out.print(i + " ");
        }
        System.out.println("\nover");
    }

    /**
     * 锁 SynchronizedExample.class 这个类的所有对象的 func4
     */
    public void func4() {
        synchronized (SynchronizedExample.class) {
            for (int i = 0; i < 100; i++) {
                System.out.print(i + " ");
            }
        }
        System.out.println("\nover");
    }


}
