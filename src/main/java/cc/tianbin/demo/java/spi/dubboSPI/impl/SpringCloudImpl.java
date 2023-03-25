package cc.tianbin.demo.java.spi.dubboSPI.impl;

import cc.tianbin.common.util.LogUtil;
import cc.tianbin.demo.java.spi.dubboSPI.DistributedArchitecture;

/**
 * Created by nibnait on 2023/03/25
 */
public class SpringCloudImpl implements DistributedArchitecture {

    @Override
    public void solve(String msg) {
        LogUtil.log("spring cloud solve: {}", msg);
    }
}
