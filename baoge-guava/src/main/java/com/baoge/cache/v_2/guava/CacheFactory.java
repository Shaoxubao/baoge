package com.baoge.cache.v_2.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * @Author shaoxubao
 * @Date 2020/2/25 15:00
 */
public class CacheFactory {


    public static Cache buildCache(long maximumSize, long timeout) {
        try {
            CacheBuilder cb = CacheBuilder.newBuilder();
            cb.maximumSize(maximumSize);
            cb.expireAfterWrite(timeout, TimeUnit.SECONDS);
            return cb.build();
        } catch (Exception e) {
            return null;
        }
    }

    public static Cache buildCache(long maximumSize, long timeout, int concurrencyLevel) {
        try {
            CacheBuilder cb = CacheBuilder.newBuilder();
            cb.maximumSize(maximumSize);
            cb.expireAfterWrite(timeout, TimeUnit.SECONDS);
            cb.concurrencyLevel(concurrencyLevel);
            return cb.build();
        } catch (Exception e) {
            return null;
        }
    }

    public static LoadingCache buildLoadingCache(long maximumSize, long timeout, CacheLoader cacheLoader) {
        try {
            LoadingCache cache = CacheBuilder.newBuilder().refreshAfterWrite(timeout, TimeUnit.SECONDS).maximumSize(maximumSize).build(cacheLoader);
            return cache;
        } catch (Exception e) {
            return null;
        }
    }

    public static LoadingCache buildLoadingCache(long maximumSize, long timeout, int concurrencyLevel, CacheLoader cacheLoader) {
        try {
            LoadingCache cache = CacheBuilder.newBuilder()
                    .refreshAfterWrite(timeout, TimeUnit.SECONDS)
                    .maximumSize(maximumSize)
                    .concurrencyLevel(concurrencyLevel)
                    .build(cacheLoader);
            return cache;
        } catch (Exception e) {
            return null;
        }
    }

    public static LoadingCache buildLoadingCacheS(long maximumSize, long timeout, CacheLoader cacheLoader) {
        try {
            LoadingCache cache = CacheBuilder.newBuilder().expireAfterWrite(timeout, TimeUnit.SECONDS).maximumSize(maximumSize).build(cacheLoader);
            return cache;
        } catch (Exception e) {
            return null;
        }
    }

}
