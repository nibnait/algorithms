package jdk.concurrent.demo.threadpool;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 简化的线程池，仅用来说明工作原理
 * Created by nibnait on 2019-09-01
 */
public class a1_MyThreadPool extends TestCase {

    class ThreadPool {
        //利用阻塞队列实现生产者-消费者模式
        BlockingQueue<Runnable> workQueue; //保存内部工作线程
        List<WorkerThread> threads = new ArrayList<>();

        // 构造方法
        public ThreadPool(int poolSize, BlockingQueue<Runnable> workQueue) {
            this.workQueue = workQueue;
            // 创建工作线程
            for (int idx = 0; idx < poolSize; idx++) {
                WorkerThread work = new WorkerThread();
                work.start();
                threads.add(work);
            }
        }

        // 提交任务
        public void execute(Runnable command) throws InterruptedException {
            workQueue.put(command);
        }

        // 工作线程负责消费任务，并执行任务
        class WorkerThread extends Thread {
            @Override
            public void run() { //循环取任务并执行
                while (true) {
                    Runnable task = null;
                    try {
                        task = workQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    task.run();
                }
            }
        }
    }

    /**
     * 下面是使用示例
     **/
    @Test
    public void testDemo() throws InterruptedException {
        // 创建有界阻塞队列
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2);
        // 创建线程池
        ThreadPool pool = new ThreadPool(10, workQueue);

        // 提交任务
        pool.execute(() -> {
            System.out.println("hello");
        });
    }
}