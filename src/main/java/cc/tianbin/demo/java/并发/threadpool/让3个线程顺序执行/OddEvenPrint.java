package cc.tianbin.demo.java.并发.threadpool.让3个线程顺序执行;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * 两个线程，依次打印奇数、偶数
 * Created by nibnait on 2023/02/01
 */
public class OddEvenPrint {

    @Test
    public void test2() {

        CyclicBarrier c = new CyclicBarrier(2);

    }

    @Test
    public void test1() {

        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(0);

        Thread oddThread = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                for (int i = 1; i <= 100; i += 2) {
                    s1.acquire();
                    System.out.println(i);
                    s2.release();
                }
            }
        });

        Thread evenThread = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                for (int i = 2; i <= 100; i += 2) {
                    s2.acquire();
                    System.out.println(i);
                    s1.release();
                }
            }
        });

        evenThread.start();
        oddThread.start();

    }

}
