package cc.tianbin.demo.java.spi.springSPI;

import cc.tianbin.common.util.LogUtil;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by nibnait on 2023/03/25
 */
public class SpringApplicationContextInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        LogUtil.log("spring 启动过程中，干一些自己的事情");
    }
}
