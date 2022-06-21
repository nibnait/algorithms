package cc.tianbin.java.并发.悲观锁vs乐观锁.reentrantLock.线程间的协作;

public class WaitNotifyExample {

    public synchronized void runNotifyAll() {
        System.out.println("notifyAll");
        notifyAll();
    }

    public synchronized void runNotify() {
        System.out.println("notify");
        notify();
    }

    public synchronized void runWait() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("wait");
    }
}
