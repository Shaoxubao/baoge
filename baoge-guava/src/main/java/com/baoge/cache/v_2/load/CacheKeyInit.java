package com.baoge.cache.v_2.load;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @Author shaoxubao
 * @Date 2020/2/27 12:24
 */

@Component
public class CacheKeyInit implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {

        initKeyConfig();
    }

    private void initKeyConfig() {
        CacheLoaderImpl.KEY_METHOD_TMP.put("a.", "");
    }
}
