package cc.tianbin.java.并发.JUC.JUC_AQS.mylock;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by nibnait on 2023/02/01
 */
public class MyLock implements Lock {

    protected static final String MY_LOCK_NAME = "myLock";

    /**
     * tryAcquire 加锁
     * tryRelease 解锁
     */
    private static class Sync extends AbstractQueuedSynchronizer {

        /**
         * 加锁
         */
        protected boolean tryAcquire(int arg) {
            if (!Thread.currentThread().getName().startsWith(MY_LOCK_NAME)) {
                // 加锁失败
                return false;
            }

            if (compareAndSetState(0, arg)) {
                setExclusiveOwnerThread(Thread.currentThread());
                // 加锁成功
                return true;
            }

            return false;
        }

        /**
         * 解锁
         */
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setState(0);

            setExclusiveOwnerThread(null);
            return true;
        }

        protected Condition getCondition() {
            return new ConditionObject();
        }
    }

    // 内部类
    Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, @NotNull TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @NotNull
    @Override
    public Condition newCondition() {
        return sync.getCondition();
    }

}
