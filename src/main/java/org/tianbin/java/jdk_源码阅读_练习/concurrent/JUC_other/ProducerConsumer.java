package org.tianbin.java.jdk_源码阅读_练习.concurrent.JUC_other;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用 BlockingQueue 实现生产者消费者问题
 *
 * Created by nibnait on 2020/11/5
 */
public class ProducerConsumer {

    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

    static class Producer extends Thread {
        @Override
        public void run() {
            try {
                System.out.println("produce..");
                queue.put("product");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer extends Thread {

        @Override
        public void run() {
            try {
                String product = queue.take();

                System.out.println("consume.." + product);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
