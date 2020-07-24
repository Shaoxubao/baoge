package com.baoge.example;

import com.google.common.cache.*;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @Author shaoxubao
 * @Date 2020/7/24 15:46
 */
public class GuavaExample {

    public static void main(String[] args) throws Exception {
        // 创建方式一：LoadingCache
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .concurrencyLevel(5)  // 并发级别设置为 5，是指可以同时写缓存的线程数
                .expireAfterWrite(8, TimeUnit.SECONDS)  // 设置 8 秒钟过期
                .initialCapacity(10)  //设置缓存容器的初始容量为 10
                .maximumSize(100)     // 设置缓存最大容量为 100，超过之后就会按照 LRU 算法移除缓存项
                .recordStats()        // 设置要统计缓存的命中率
                .removalListener(new RemovalListener<Object, Object>() { // 设置缓存的移除通知
                    @Override
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause());
                    }
                }).build(new CacheLoader<String, String>() { // 指定 CacheLoader，缓存不存在时，可自动加载缓存
                    @Override
                    public String load(String key) throws Exception {
                        // 自动加载缓存的业务
                        return "cache-value:" + key;
                    }
                });

        loadingCache.put("c1", "hello, c1");
        String value1 = loadingCache.get("c1");
        System.out.println(value1);

        // 查询不存在的
        System.out.println(loadingCache.get("novalue"));

        // 创建方式二：Callable
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(2).build();

        cache.put("k1", "hello k1");
        String value2 = cache.get("k1", new Callable<String>() {
            @Override
            public String call() throws Exception {
                // 缓存不存在时，执行
                return "nil";
            }
        });
        System.out.println(value2);

        String value3 = cache.get("k2", new Callable<String>() {
            @Override
            public String call() throws Exception {
                // 缓存不存在时，执行
                return "nil";
            }
        });
        System.out.println(value3);
    }

}
