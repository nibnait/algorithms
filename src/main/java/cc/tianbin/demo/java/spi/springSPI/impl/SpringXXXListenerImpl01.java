package cc.tianbin.demo.java.spi.springSPI.impl;

import cc.tianbin.common.util.LogUtil;
import cc.tianbin.demo.java.spi.springSPI.SpringXXXListener;

/**
 * Created by nibnait on 2023/03/25
 */
public class SpringXXXListenerImpl01 implements SpringXXXListener {
    @Override
    public void run() {
        LogUtil.log("SpringXXXListenerImpl01");
    }
}
