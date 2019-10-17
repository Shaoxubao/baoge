package utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.UUID;

/**
 * @Author shaoxubao
 * @Date 2019/10/16 14:56
 */
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(String name, Class<T> t) {
        return applicationContext.getBean(name, t);
    }

    public static <T> T getBean(Class<T> t) {
        return applicationContext.getBean(t);
    }

    public static String getCacheKey(String prefix, String key) {
        return prefix + key;
    }

    public static String generateNewJessionId() {
        return StringUtils.remove(UUID.randomUUID().toString(), '-');
    }
}
