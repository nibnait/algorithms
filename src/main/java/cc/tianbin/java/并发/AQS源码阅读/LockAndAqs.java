package cc.tianbin.java.并发.AQS源码阅读;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by nibnait on 2023/02/01
 */
public class LockAndAqs {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {


        try {
            lock.lock();
        } finally {
            lock.unlock();
        }



    }

}
