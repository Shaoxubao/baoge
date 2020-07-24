package com.baoge.example.myimpl_cache;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author shaoxubao
 * @Date 2020/7/24 16:39
 *
 * 缓存操作工具类
 */
public class CacheUtils {

    public CacheUtils() {
        new Thread(new ExpireThread()).start();
    }

    /**
     * 添加缓存
     */
    public void put(String key, Object value, long expire) {
        // 非空判断，借助 commons-lang3
        if (StringUtils.isBlank(key)) return;
        // 当缓存存在时，更新缓存
        if (CacheGlobal.concurrentHashMap.containsKey(key)) {
            CacheValue cache = CacheGlobal.concurrentHashMap.get(key);
            cache.setHitCount(cache.getHitCount() + 1);
            cache.setWriteTime(System.currentTimeMillis());
            cache.setLastTime(System.currentTimeMillis());
            cache.setExpireTime(expire);
            cache.setValue(value);
            return;
        }
        // 创建缓存
        CacheValue cache = new CacheValue();
        cache.setKey(key);
        cache.setValue(value);
        cache.setWriteTime(System.currentTimeMillis());
        cache.setLastTime(System.currentTimeMillis());
        cache.setHitCount(1);
        cache.setExpireTime(expire);
        CacheGlobal.concurrentHashMap.put(key, cache);
    }

    /**
     * 获取缓存
     */
    public Object get(String key) {
        // 非空判断
        if (StringUtils.isBlank(key)) return null;
        // 字典中不存在
        if (CacheGlobal.concurrentHashMap.isEmpty()) return null;
        if (!CacheGlobal.concurrentHashMap.containsKey(key)) return null;
        CacheValue cache = CacheGlobal.concurrentHashMap.get(key);
        if (cache == null) return null;
        // 惰性删除，判断缓存是否过期
        long timeoutTime = System.currentTimeMillis() - cache.getWriteTime();
        // 缓存过期
        if (cache.getExpireTime() <= timeoutTime) {
            // 清除过期缓存
            CacheGlobal.concurrentHashMap.remove(key);
            return null;
        }
        cache.setHitCount(cache.getHitCount() + 1);
        cache.setLastTime(System.currentTimeMillis());
        return cache.getValue();
    }
}
