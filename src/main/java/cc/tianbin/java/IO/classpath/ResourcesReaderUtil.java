package cc.tianbin.java.IO.classpath;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by nibnait on 2020/11/12
 */
public class ResourcesReaderUtil {
    public InputStream getResource(String fileName) throws IOException {
        return this.getClass().getResourceAsStream(fileName);
    }
}
