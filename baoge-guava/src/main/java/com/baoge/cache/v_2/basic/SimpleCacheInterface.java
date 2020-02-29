package com.baoge.cache.v_2.basic;

import java.util.concurrent.Callable;

/**
 * @Author shaoxubao
 * @Date 2020/2/25 15:00
 */
public interface SimpleCacheInterface {

    Object get(String cacheName, Object key);

    Object get(String cacheName, Object key, Callable<Object> callable);

    void set(String cacheName, Object key, Object value);

    String getAllCachedKeys(String cacheName);

    String getAllCachedKeys();

    void clear(String cacheName, Object key);

    void clearBatch(String cacheName);

    long getSize(String cacheName);

    String getCacheNames();

    String pageCachedKeys(int next, String condition);

}
