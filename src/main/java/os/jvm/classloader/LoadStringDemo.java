package os.jvm.classloader;

import org.junit.Test;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by nibnait on 2020/11/11
 */
public class LoadStringDemo {

    @Test
    public void testGetAppClassLoaderUrls() {
        URLClassLoader systemClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();

        URL[] urLs = systemClassLoader.getURLs();
        for (URL url: urLs) {
            System.out.println(url);
        }
    }

    @Test
    public void testGetExtClassLoaderUrls() {
        URLClassLoader systemClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader().getParent();

        URL[] urLs = systemClassLoader.getURLs();
        for (URL url: urLs) {
            System.out.println(url);
        }
    }
}
