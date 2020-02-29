package com.baoge.cache.v_2.config;

import com.baoge.cache.v_2.guava.CacheFactory;
import com.baoge.cache.v_2.load.CacheLoaderImpl;
import com.google.common.cache.LoadingCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author shaoxubao
 * @Date 2020/2/27 9:20
 */

@Configuration
public class CacheConfig {

    @Bean
    public LoadingCache loadingCache() {

        return CacheFactory.buildLoadingCache(30000, 120, new CacheLoaderImpl());
    }

}
