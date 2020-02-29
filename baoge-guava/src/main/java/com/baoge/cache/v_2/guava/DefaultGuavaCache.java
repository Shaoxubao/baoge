package com.baoge.cache.v_2.guava;

import com.baoge.cache.v_2.basic.DefaultCacheInterface;
import com.google.common.cache.Cache;

import java.util.concurrent.Callable;

/**
 * @Author shaoxubao
 * @Date 2020/2/25 15:01
 */
public class DefaultGuavaCache extends SimpleGuavaCache implements DefaultCacheInterface {

    private Cache cache;
    public final static String DEFAULT_CACHE_NAME = "cache_default";

    public DefaultGuavaCache(long maximumSize, long timeout) {
        cache = CacheFactory.buildCache(maximumSize, timeout);
        addToCache(DEFAULT_CACHE_NAME, cache);
    }

    public Object get(Object key) {
        return super.get(DEFAULT_CACHE_NAME, key);
    }

    public Object get(Object key, Callable<Object> callable) {
        return super.get(DEFAULT_CACHE_NAME, key, callable);
    }

    public void clear() {
        super.clearBatch(DEFAULT_CACHE_NAME);
    }

    public void clear(Object key) {
        super.clear(DEFAULT_CACHE_NAME, key);
    }

    public long getSize() {
        return super.getSize(DEFAULT_CACHE_NAME);
    }

    public String getAllCachedKeys() {
        return super.getAllCachedKeys(DEFAULT_CACHE_NAME);
    }

    @Override
    public String pageCachedKeys(int next, String condition) {
        return null;
    }

    public void set(Object key, Object value) {
        super.set(DEFAULT_CACHE_NAME, key, value);
    }

}
