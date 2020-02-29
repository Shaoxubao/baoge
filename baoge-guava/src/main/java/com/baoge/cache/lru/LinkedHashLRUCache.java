package com.baoge.cache.lru;

import com.google.common.base.Preconditions;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2020/2/29
 *
 * This class is not thread-safe class.
 */
public class LinkedHashLRUCache<K, V> implements LRUCache<K, V> {

    private final int limit;

    private final InternalLRUCache<K, V> internalLRUCache;

    private static class InternalLRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int limit;

        public InternalLRUCache(int limit) {
            super(16, 0.75f, true);
            this.limit = limit;
        }

        /**
         * 如果Map的尺寸大于设定的最大长度，返回true，再新加入对象时删除最老的对象
         */
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > limit;
        }
    }

    public LinkedHashLRUCache(int limit) {
        Preconditions.checkArgument(limit > 0, "param limit must big than zero.");
        this.limit = limit;
        this.internalLRUCache = new InternalLRUCache(limit);
    }

    @Override
    public void put(K key, V value) {
        this.internalLRUCache.put(key, value);
    }

    @Override
    public V get(K key) {
        return this.internalLRUCache.get(key);
    }

    @Override
    public void remove(K key) {
        this.internalLRUCache.remove(key);
    }

    @Override
    public int size() {
        return this.internalLRUCache.size();
    }

    @Override
    public void clear() {
        this.internalLRUCache.clear();
    }

    @Override
    public int limit() {
        return this.limit;
    }

    @Override
    public String toString() {
        return internalLRUCache.toString();
    }
}
