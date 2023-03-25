package cc.tianbin.demo.java.spi.dubboSPI;

import org.apache.dubbo.common.extension.SPI;

/**
 * Created by nibnait on 2023/03/25
 */
@SPI("dubbo")
public interface DistributedArchitecture {
    void solve(String msg);
}
