package com.baoge.guava;

import com.baoge.entity.Employee;
import com.google.common.base.Optional;
import com.google.common.cache.*;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.fail;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2020/3/3
 */
public class CacheLoaderTest3 {

    @Test
    public void testLoadNullValue() {
        CacheLoader<String, Employee> cacheLoader = CacheLoader
                .from(k -> k.equals("null") ? null : new Employee(k, k, k));
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder().build(cacheLoader);

        Employee alex = cache.getUnchecked("Alex");
        assertThat(alex, notNullValue());
        assertThat(alex.getName(), equalTo("Alex"));

        try {
            assertThat(cache.getUnchecked("null"), nullValue());
            fail("should not process here.");
        } catch (Exception e){
            assertThat(e instanceof CacheLoader.InvalidCacheLoadException, equalTo(true));
        }

    }

    @Test
    public void testLoadNullValueUserOptional() {
        CacheLoader<String, Optional<Employee>> loader = new CacheLoader<String, Optional<Employee>>() {
            @Override
            public Optional<Employee> load(String key) throws Exception {
                if (key.equals("null"))
                    return Optional.fromNullable(null);
                else
                    return Optional.fromNullable(new Employee(key, key, key));
            }
        };

        LoadingCache<String, Optional<Employee>> cache = CacheBuilder.newBuilder().build(loader);
        assertThat(cache.getUnchecked("Alex").get(), notNullValue());
        assertThat(cache.getUnchecked("null").orNull(), nullValue());

        Employee def = cache.getUnchecked("null").or(new Employee("default", "default", "default"));
        assertThat(def.getName().length(), equalTo(7));
    }

    @Test
    public void testCacheRefresh() throws Exception {
        AtomicInteger counter = new AtomicInteger(0);

        CacheLoader<String, Long> cacheLoader = CacheLoader
                .from(key -> {
                    counter.incrementAndGet();
                    return System.currentTimeMillis();
                });

        LoadingCache<String, Long> cache = CacheBuilder.newBuilder()
                .refreshAfterWrite(2, TimeUnit.SECONDS)
                .build(cacheLoader);

        Long result1 = cache.getUnchecked("Alex");
        TimeUnit.SECONDS.sleep(3); // 休眠3秒后 去cache中取Alex，超过设置的2秒超时，cache做法是从loader重新取
        Long result2 = cache.getUnchecked("Alex");

        assertThat(result1.longValue() != result2.longValue(), equalTo(true));

        assertThat(counter.get(), equalTo(2));
    }

    @Test
    public void testCachePreload() {
        CacheLoader<String, String> cacheLoader = CacheLoader.from(String::toUpperCase);
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().build(cacheLoader);

        Map<String, String> preData = new HashMap<String, String>() {
            {
                put("alex", "ALEX");
                put("hello", "hello");
            }
        };

        cache.putAll(preData);

        assertThat(cache.size(), equalTo(2L));
        assertThat(cache.getUnchecked("alex"), equalTo("ALEX"));
        assertThat(cache.getUnchecked("hello"), equalTo("hello")); // 正确

        assertThat(cache.getUnchecked("baoge"), equalTo("baoge")); // 报错（String::toUpperCase会将value转大写）

    }

    @Test
    public void testCacheRemovedNotify() {
        CacheLoader<String, String> cacheLoader = CacheLoader.from(String::toUpperCase);
        RemovalListener<String, String> listener = notification -> {
          if (notification.wasEvicted()) { // 被逐出
              RemovalCause cause = notification.getCause();
              assertThat(cause, Is.is(RemovalCause.SIZE));

              // 判断被逐出的key
              assertThat(notification.getKey(), equalTo("Alex"));
          }
        };
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .removalListener(listener)
                .build(cacheLoader);

        cache.getUnchecked("Alex");
        cache.getUnchecked("Jack");
        cache.getUnchecked("Tom");
        cache.getUnchecked("Tony");



    }

}
