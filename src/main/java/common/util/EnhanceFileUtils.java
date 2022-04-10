package common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

/**
 * Created by nibnait on 2022/03/02
 */
@Slf4j
public class EnhanceFileUtils {

    public static File createIfNecessary(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            if (!file.createNewFile()) {
                log.error("FileUtils createIfNecessary failed");
            }
        }
        return file;
    }

}
