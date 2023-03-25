package cc.tianbin.demo.java.spi.javaSPI.impl;

import cc.tianbin.common.util.LogUtil;
import cc.tianbin.demo.java.spi.javaSPI.Log;

/**
 * Created by nibnait on 2023/03/25
 */
public class Sfl4jImpl implements Log {
    @Override
    public void print(String content) {
        LogUtil.log("slf4j print: \t{}", content);
    }
}