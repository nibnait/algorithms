package cc.tianbin.demo.java.spi.dubboSPI;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

import java.net.URL;
import java.util.Map;

/**
 * Created by nibnait on 2023/03/25
 */
public class Dubbo_SPI_Main {


    /**
     * @see ExtensionLoader#getExtension(String, boolean)
     * @see ExtensionLoader#createExtension(String, boolean)
     *
     *
     * @see ExtensionLoader#getExtensionClasses()
     * Map<String, Class<?>> cachedClasses
     * @see ExtensionLoader#loadExtensionClasses()
     * @see ExtensionLoader#loadResource(Map, ClassLoader, URL, boolean, String...)
     * @see ExtensionLoader#loadClass(Map, URL, Class, String, boolean)
     * @see ExtensionLoader#saveInExtensionClass(Map, Class, String, boolean)
     */
    @Test
    public void test() {
        ExtensionLoader<DistributedArchitecture> extensionLoader = ExtensionLoader.getExtensionLoader(DistributedArchitecture.class);
//        DistributedArchitecture distributedArchitecture = extensionLoader.getExtension("dubbo");
//        DistributedArchitecture distributedArchitecture = extensionLoader.getExtension("servicemesh");
//        DistributedArchitecture distributedArchitecture = extensionLoader.getExtension("springcloud");
        DistributedArchitecture distributedArchitecture = extensionLoader.getDefaultExtension();
        distributedArchitecture.solve("test");
    }

}
