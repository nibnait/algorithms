package jdk.concurrent.demo.threadpool;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Vector;
import java.util.concurrent.*;

/**
 * CountDownLanch 和 CyclicBarrier
 * Created by nibnait on 2019-09-01
 */
public class a0_多线程同步 extends TestCase {

    // 未对账订单
    private ProcessOrder pos;
    // 派送单
    private DeliveryOrder dos;

    /**
     * 在主线程中，子线程调用join方法，使步调一致
     *
     * @throws InterruptedException
     */
    public void Main_Join() throws InterruptedException {

        while (existUnCheckOrders()) {    //存在未对账订单
            // 查询未对账订单
            Thread T1 = new Thread(() -> {
                pos = getPOrders();
            });
            T1.start();

            // 查询运单
            Thread T2 = new Thread(() -> {
                dos = getDOrders();
            });
            T2.start();

            // 等待 T1、T2 结束
            T1.join();
            T2.join();

            // 执行对账操作
            Order diff = checkOrder(pos, dos);
            // 差异写入差异库
            save(diff);
        }
    }

    /**
     * 使用 CountDownLatch 使线程步调一致
     * 有点：使用线程池，减少了线程创建的耗时，提高了线程利用率
     * 缺点：只能核对一次，CountDownLatch就归零了
     *
     * @throws InterruptedException
     */
    public void testCountDownLanch() throws InterruptedException {
        // 创建 2 个线程的线程池
//        Executor executor = Executors.newFixedThreadPool(2);  // 尽量使用new ThreadPoolExecutor 显式创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());

        while (existUnCheckOrders()) {    //存在未对账订单
            // 计数器初始化为 2
            CountDownLatch latch = new CountDownLatch(2);
            // 查询订单
            executor.execute(() -> {
                pos = getPOrders();
                latch.countDown();
            });
            // 查询运单
            executor.execute(() -> {
                dos = getDOrders();
                latch.countDown();
            });

            // 等待两个查询操作结束
            latch.await();

            // 执行对账操作
            Order diff = checkOrder(pos, dos);
            // 差异写入差异库
            save(diff);
        }
    }

    /**
     * 使用 CyclicBarrier
     */
    @Test
    public void testCyclicBarrier() {
        CyclicBarrierTest cyclicBarrierTest = new CyclicBarrierTest();
        /**
         * 只需调用一次checkAll方法，在两个线程执行完毕，就会回调CyclicBarrier 中的check方法进行对账
         *
         * executor线程池大小应该为 1，
         * 原因1：check方法的耗时远低于getOrder方法，所以1个线程完全可以处理过来
         * 原因2：如果多线程来执行check，有可能造成某一时刻check的是A线程的POrder和B线程的DOrder，数据混乱。
         */
        cyclicBarrierTest.checkAll();
    }

    class CyclicBarrierTest {
        // 订单队列
        Vector<ProcessOrder> pos;
        // 派送单队列
        Vector<DeliveryOrder> dos;
        // 执行回调的线程池
        Executor executor = Executors.newFixedThreadPool(1);
        final CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            executor.execute(() -> check());
        });


        private void check() {
            ProcessOrder p = pos.remove(0);
            DeliveryOrder d = dos.remove(0);
            // 执行对账操作
            Order diff = checkOrder(p, d);
            // 差异写入差异库
            save(diff);
        }

        public void checkAll() {
            // 循环查询订单库
            Thread T1 = new Thread(() -> {
                while (existUnCheckOrders()) {    //存在未对账订单
                    // 查询订单库
                    pos.add(getPOrders());
                    // 等待
                    try {
                        barrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            T1.start();

            // 循环查询运单库
            Thread T2 = new Thread(() -> {
                while (existUnCheckOrders()) {    //存在未对账订单
                    // 查询运单库
                    dos.add(getDOrders());
                    // 等待
                    try {
                        barrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            T2.start();
        }

    }

    /**
     * 对账单
     */
    class ProcessOrder {

    }

    /**
     * 运单
     */
    class DeliveryOrder {

    }

    /**
     * 对账单和运单之间的差异单
     */
    class Order {

    }

    private void save(Order diff) {
    }

    private Order checkOrder(ProcessOrder pos, DeliveryOrder dos) {
        return new Order();
    }

    private DeliveryOrder getDOrders() {
        return new DeliveryOrder();
    }

    private ProcessOrder getPOrders() {
        return new ProcessOrder();
    }

    private boolean existUnCheckOrders() {
        return true;
    }


}
