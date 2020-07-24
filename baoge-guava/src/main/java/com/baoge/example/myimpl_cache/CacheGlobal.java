package com.baoge.example.myimpl_cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author shaoxubao
 * @Date 2020/7/24 16:18
 *
 * Cache 全局类
 */
public class CacheGlobal {
    // 全局缓存对象
    public static ConcurrentHashMap<String, CacheValue> concurrentHashMap = new ConcurrentHashMap<>();
}
