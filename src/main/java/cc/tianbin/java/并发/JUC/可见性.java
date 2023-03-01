package cc.tianbin.java.并发.JUC;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by nibnait on 2020/11/23
 */
public class 可见性 {

    private Number number = new Number();
    private int loopCount = 100000;

    private volatile Integer count = 0;

    @Test
    public void testVolatile() {
        count = count + 1;
        System.out.println(count);
    }

    @Test
    public void test可见性() {

        int corePoolSize = 100;
        int maximumPoolSize = 200;
        ThreadPoolExecutor addAThreadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue(1000),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        for (int i = 0; i < loopCount; i++) {
            addAThreadPool.execute(new AddA());
        }


        ThreadPoolExecutor addBThreadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue(1000),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        for (int i = 0; i < loopCount; i++) {
            addBThreadPool.execute(new AddB());
        }


        ThreadPoolExecutor addCThreadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue(1000),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        for (int i = 0; i < loopCount; i++) {
            addCThreadPool.execute(new AddC());
        }


        ThreadPoolExecutor addDThreadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue(1000),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        for (int i = 0; i < loopCount; i++) {
            addDThreadPool.execute(new AddD());
        }


        ThreadPoolExecutor addEThreadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue(1000),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        for (int i = 0; i < loopCount; i++) {
            addEThreadPool.execute(new AddE());
        }


        ThreadPoolExecutor addFThreadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue(1000),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        for (int i = 0; i < loopCount; i++) {
            addFThreadPool.execute(new AddF());
        }

        System.out.println(JSON.toJSONString(number));

    }

/*
    Number{a=999961, b=999968, c=999994, d=1000000}

    a. 不用说了，子线程改的是线程副本里的值，然后再写回主内存。并发情况 数据会不一致
    b. 虽然加了 volatile 保证 b 这个变量只有1份，但是在 1号线程更改b值的过程中，2号线程也可能会正在修改，导致并发。
    c. 虽然加了 synchronize, 但是他只保证了【get 从主内存取值到线程副本中】【set 从线程本地副本写回内存】这两个单独的操作是原子的。
    d. CAS的方式保证自增成功，和数据一致性。

    正确使用 synchronize 关键字：
    {"a":99994,"b":99992,"c":99978,"d":100000,"e":99993,"f":100000}

    e. 给整个 run 方法加锁，保证整个【get 从主内存取值到线程副本中】和【set 在线程本地副本写回主内存】是原子的。作用相当于volatile关键字。
    无法保证并发。
    f. 给 AddE 这个类加锁。才能保证 +1 操作是串行的。
*/

    class Number {
        int a;
        volatile int b;
        int c;
        AtomicInteger d;
        int e;
        int f;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        public synchronized int getC() {
            return c;
        }

        public synchronized void setC(int c) {
            this.c = c;
        }

        public AtomicInteger getD() {
            return d;
        }

        public void setD(AtomicInteger d) {
            this.d = d;
        }

        public int getE() {
            return e;
        }

        public void setE(int e) {
            this.e = e;
        }

        public int getF() {
            return f;
        }

        public void setF(int f) {
            this.f = f;
        }

        public Number() {
            this.d = new AtomicInteger(0);
        }
    }


    class AddA extends Thread {
        @Override
        public void run() {
            int a = number.getA() + 1;
            number.setA(a);
        }
    }

    class AddB extends Thread {
        @Override
        public void run() {
            int b = number.getB() + 1;
            number.setB(b);
        }
    }

    class AddC extends Thread {
        @Override
        public void run() {
            int c = number.getC() + 1;
            number.setC(c);
        }
    }

    class AddD extends Thread {
        @Override
        public void run() {
            number.getD().getAndIncrement();
        }
    }

    class AddE extends Thread {
        @Override
        public synchronized void run() {
            int e = number.getE() + 1;
            number.setE(e);
        }
    }

    class AddF extends Thread {
        @Override
        public void run() {
            synchronized (AddF.class) {
                int f = number.getF() + 1;
                number.setF(f);
            }
        }
    }

}
