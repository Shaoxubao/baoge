package com.baoge.v_2.basic;

/**
 * @Author shaoxubao
 * @Date 2020/2/25 14:59
 */
public interface DefaultCacheInterface {

    Object get(Object key);

    void set(Object key, Object value);

    String getAllCachedKeys();

    void clear();

    void clear(Object key);

    long getSize();
}
