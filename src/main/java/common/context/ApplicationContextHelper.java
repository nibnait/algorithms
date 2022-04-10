package common.context;

import io.github.nibnait.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

import java.beans.Introspector;
import java.util.Map;

/**
 * Created by nibnait on 2021/09/01
 */
@Slf4j
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static BeanDefinitionRegistry beanDefinitionRegistry;

    /**
     * 动态注册bean
     */
    public synchronized static void registerBean(String beanName, Class clazz) {
        if (null == beanName || null == clazz) {
            throw new RuntimeException(beanName + "注册失败");
        }

        BeanDefinition beanDefinition = getBeanDefinitionBuilder(clazz).getBeanDefinition();
        if (!beanDefinitionRegistry.containsBeanDefinition(beanName)) {
            beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinition);
        }
    }


    private static BeanDefinitionBuilder getBeanDefinitionBuilder(Class clazz) {
        return BeanDefinitionBuilder.genericBeanDefinition(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHelper.applicationContext = applicationContext;
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        beanDefinitionRegistry = (BeanDefinitionRegistry) configurableApplicationContext.getBeanFactory();
    }

    public static <T> T getBean(Class<T> targetClz) {
        T beanInstance = null;
        //byType
        try {
            beanInstance = applicationContext.getBean(targetClz);
        } catch (Exception e) {
            log.error("ApplicationContextHelper getBean error ", e);
        }
        //byName
        if (beanInstance == null) {
            String simpleName = Introspector.decapitalize(targetClz.getSimpleName());
            try {
                Object bean = applicationContext.getBean(simpleName);
                if (bean == null) {
                    throw new ServiceException("获取bean失败 {}", targetClz);
                }
                beanInstance = targetClz.cast(bean);
            } catch (Exception e) {
                log.error("ApplicationContextHelper getBean beanInstance error", e);
            }
        }
        return beanInstance;
    }

    public static <T> T getBean(String beanName, Class<T> targetClz) {
        return applicationContext.getBean(beanName, targetClz);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }

}
