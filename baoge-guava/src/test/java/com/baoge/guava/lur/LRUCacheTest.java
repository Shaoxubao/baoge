package com.baoge.guava.lur;

import com.baoge.cache.lru.LRUCache;
import com.baoge.cache.lru.LinkedHashLRUCache;
import com.baoge.cache.lru.LinkedListLRUCache;
import com.baoge.cache.lru.SoftLRUCache;
import com.baoge.cache.reference.ReferenceDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2020/2/29
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class LRUCacheTest {

    @Test
    public void testLruCache() {
        LRUCache<String, String> cache = new LinkedHashLRUCache<>(3);
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        System.out.println(cache);

        cache.put("4", "4");
        System.out.println(cache);

        System.out.println(cache.get("2"));
        System.out.println(cache);

    }

    @Test
    public void testLinkedListLRUCache() {
        LRUCache<String, String> cache = new LinkedListLRUCache<>(3);
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        System.out.println(cache);

        cache.put("4", "4");
        System.out.println(cache);

        System.out.println(cache.get("2"));
        System.out.println(cache);
    }

    // vm参数：-Xmx128M -Xms64M -XX:+PrintGCDetails
    // 运行之后会堆内存溢出，继续看下个test
    @Test
    public void testLinkedHashLRUCache() throws InterruptedException {
        LinkedHashLRUCache<String, byte[]> cache = new LinkedHashLRUCache<>(100);
        for (int i = 0; i < 100; i++) {
            cache.put(String.valueOf(i), new byte[1024 * 1024 * 2]);
            TimeUnit.MILLISECONDS.sleep(600);
            System.out.println("The " + i + "is cached.");
        }
    }

    /**
     * 参考 {@link ReferenceDemo
      */
    @Test
    public void testSoftLRUCache() throws InterruptedException {
        final SoftLRUCache<String, byte[]> cache = new SoftLRUCache<>(100);
        for (int i = 0; i < 100; i++) {
            cache.put(String.valueOf(i), new byte[1024 * 1024 * 2]);
            TimeUnit.MILLISECONDS.sleep(600);
            System.out.println("The " + i + "is cached.");
        }
    }

}
