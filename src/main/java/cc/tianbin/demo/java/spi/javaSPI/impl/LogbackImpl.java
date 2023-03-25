package cc.tianbin.demo.java.spi.javaSPI.impl;

import cc.tianbin.common.util.LogUtil;
import cc.tianbin.demo.java.spi.javaSPI.Log;

/**
 * Created by nibnait on 2023/03/25
 */
public class LogbackImpl implements Log {
    @Override
    public void print(String content) {
        LogUtil.log("Logback print: \t{}", content);
    }
}
