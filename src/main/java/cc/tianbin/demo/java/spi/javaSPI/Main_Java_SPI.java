package cc.tianbin.demo.java.spi.javaSPI;

import org.junit.Test;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by nibnait on 2023/03/25
 */
public class Main_Java_SPI {

    /**
     * 约定大于配置
     * 找到 resources/META-INF/services/ 下的 cc.tianbin.demo.java.spi.javaSPI.Log 文件

     */
    @Test
    public void test() {
        ServiceLoader<Log> serviceLoader = ServiceLoader.load(Log.class);
        Iterator<Log> iterator = serviceLoader.iterator();

        /**
         * @see Iterator#hasNext()
         * @see ServiceLoader.LazyIterator#hasNext()
         * @see ServiceLoader.LazyIterator#hasNextService()
         */
        while (iterator.hasNext()) {

            /**
             * @see Iterator#next()
             * @see ServiceLoader.LazyIterator#next()
             * @see ServiceLoader.LazyIterator#nextService()
             */
            Log log = iterator.next();
            log.print("hello world");
        }

    }

}
