package jdk.concurrent.demo;

import jdk.concurrent.AQS.Semaphore;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;
import java.util.Vector;
import java.util.function.Function;

/**
 * 一个对象池
 * 使用Semaphore 信号量来做限流
 *
 * @author nibnait
 * @version $Id: ObjectPool.java, v 0.1 2019-08-29 下午4:48 nibnait Exp $$
 */
public class ObjectPool extends TestCase {

    class Pool<T, R> {
        List<T> pool;

        Semaphore semaphore;

        public Pool(int size, T t) {
            this.pool = new Vector<T>() {
            };
            for (int i = 0; i < size; i++) {
                pool.add(t);
            }
            this.semaphore = new Semaphore(size);
        }

        // 利用对象池的对象，调用func
        R exec(Function<T, R> func) throws InterruptedException {
            T t = null;
            try {
                semaphore.acquire();
                t = pool.remove(0);
                return func.apply(t);
            } finally {
                pool.add(t);
                semaphore.release();
            }
        }
    }

    // 创建对象池
    @Test
    public void testMain() throws InterruptedException {
        Pool<Integer, String> pool = new Pool<Integer, String>(10, 2);
        // 通过对象池获取t，之后执行
        pool.exec(t -> {
            System.out.println("方法开始执行。。。。入参：" + t);
            return t.toString();
        });
    }

}