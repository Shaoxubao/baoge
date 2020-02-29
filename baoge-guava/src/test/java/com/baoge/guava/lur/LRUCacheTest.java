package com.baoge.guava.lur;

import com.baoge.cache.lru.LRUCache;
import com.baoge.cache.lru.LinkedHashLRUCache;
import com.baoge.cache.lru.LinkedListLRUCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

}
