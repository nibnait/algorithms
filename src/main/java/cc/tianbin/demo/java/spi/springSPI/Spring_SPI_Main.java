package cc.tianbin.demo.java.spi.springSPI;

import org.junit.Test;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;

/**
 * Created by nibnait on 2023/03/25
 */
public class Spring_SPI_Main {

    /**
     * @see SpringFactoriesLoader#loadFactories(Class, ClassLoader)
     * @see SpringFactoriesLoader#loadFactoryNames(Class, ClassLoader)
     *
     */
    @Test
    public void test() {
        List<SpringXXXListener> springXXXListeners = SpringFactoriesLoader.loadFactories(SpringXXXListener.class, Thread.currentThread().getContextClassLoader());
        for (SpringXXXListener listener : springXXXListeners) {
            listener.run();
        }

    }

    @Test
    public void 扩展SpringSPI() {
        List<ApplicationContextInitializer> initializers = SpringFactoriesLoader.loadFactories(ApplicationContextInitializer.class, Thread.currentThread().getContextClassLoader());

        System.out.println(initializers.size());
        for (ApplicationContextInitializer initializer : initializers) {
            System.out.println(initializer);
        }

    }

}
