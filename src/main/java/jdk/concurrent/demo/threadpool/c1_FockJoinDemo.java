package jdk.concurrent.demo.threadpool;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool 单机版的MapReduce
 * <p>
 * 用Fork/Join这个并行计算框架 计算斐波那契数列
 *
 * Created by nibnait on 2019-09-01
 */
public class c1_FockJoinDemo extends TestCase {

    @Test
    public void testMain() {
        //创建分治任务线程池
        ForkJoinPool fjp = new ForkJoinPool(4);
        //创建分治任务
        Fibonacci fib = new Fibonacci(30);
        //启动分治任务
        Integer result = fjp.invoke(fib);
        //输出结果
        System.out.println(result);
    }

    //递归任务
    class Fibonacci extends RecursiveTask<Integer> {
        final int n;

        Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }
            Fibonacci f1 = new Fibonacci(n - 1);
            //创建子任务
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);

            //等待子任务结果，并合并结果
            return f2.compute() + f1.join();
        }
    }
}
