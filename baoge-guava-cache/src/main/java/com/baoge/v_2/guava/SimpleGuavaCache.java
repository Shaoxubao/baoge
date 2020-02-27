package com.baoge.v_2.guava;

import com.alibaba.fastjson.JSONObject;
import com.baoge.v_2.basic.SimpleCacheInterface;
import com.google.common.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author shaoxubao
 * @Date 2020/2/25 15:02
 */

@Slf4j
@Service
public class SimpleGuavaCache implements SimpleCacheInterface {

    public final static String DEFAULT_CACHE_NAME = "cache_default";
    private static String FLAG = "%s~%s";
    private static String FLAG_KEYS = "keys";
    private static String FLAG_PAGE_SIZE = "pageSize";
    private static String FLAG_KEY_SIZE = "keySize";
    private final static int PAGESIZE = 20;
    private static Map<String, Cache> caches = new ConcurrentHashMap();

    static {
        Cache cache = CacheFactory.buildCache(3000, 120);
        caches.put(DEFAULT_CACHE_NAME, cache);
    }

    public Cache getCache(String cacheName) {
        if (caches.isEmpty()) return null;
        return caches.get(cacheName);
    }

    public Object get(String cacheName, Object key) {
        try {
            Cache cache = getCache(cacheName);
            if (cache == null) return null;
            return cache.getIfPresent(key);
        } catch (Exception e) {
            return null;
        }
    }

    public Object get(String cacheName, Object key, Callable<Object> callable) {
        try {
            Cache cache = getCache(cacheName);
            if (cache == null) return null;
            return cache.get(key, callable);
        } catch (Exception e) {
            return null;
        }
    }

    public void set(String cacheName, Object key, Object value) {
        try {
            Cache cache = getCache(cacheName);
            if (cache == null) return;
            cache.put(key, value);
        } catch (Exception e) {

        }
    }

    public String getAllCachedKeys(String cacheName) {
        Cache cache = getCache(cacheName);
        if (cache == null) return null;
        return JSONObject.toJSONString(cache.asMap().keySet());
    }

    public String getAllCachedKeys() {
        List<String> r = new ArrayList<>();
        Set<Map.Entry<String, Cache>> set = caches.entrySet();

        for (Map.Entry<String, Cache> entry : set) {
            Cache c = entry.getValue();
            String cache_name = entry.getKey();
            Set<String> s = c.asMap().keySet();

            for (String key : s) {
                String tmp = String.format(FLAG, key, cache_name);
                r.add(tmp);
            }
        }
        return JSONObject.toJSONString(r);
    }

    public void clear(String cacheName, Object key) {
        Cache cache = getCache(cacheName);
        if (cache == null) return;
        cache.invalidate(key);
    }

    public void clearBatch(String cacheName) {
        Cache cache = getCache(cacheName);
        if (cache == null) return;
        cache.invalidateAll();
    }

    public void addToCache(String cacheName, Cache cache) {
        if (caches.containsKey(cacheName)) {
            log.info("add to cache map failed. cacheName[" + cacheName + "] already exists!");
            return;
        }
        this.caches.put(cacheName, cache);
    }

    public long getSize(String cacheName) {
        Cache cache = getCache(cacheName);
        if (cache == null) return 0;
        return cache.size();
    }

    public String getCacheNames() {
        return JSONObject.toJSONString(caches.keySet());
    }

    @Override
    public String pageCachedKeys(int next, String condition) {
        List<String> r = new ArrayList<>();
        Set<Map.Entry<String, Cache>> set = caches.entrySet();
        int k = 0;
        int size = 0;
        int[] indexs = getIndex(next);
        int begin = indexs[0];
        int end = indexs[1];

        for (Map.Entry<String, Cache> entry : set) {
            Cache c = entry.getValue();
            Set<String> s = c.asMap().keySet();
            size = size + s.size();

            for (String key : s) {
                if (!StringUtils.isEmpty(condition)) {
                    if (key.contains(condition)) {
                        r.add(key);
                    }
                    continue;
                }

                if (k >= begin && k < end) {
                    r.add(key);
                }
                k++;
            }
        }
        size = StringUtils.isEmpty(condition) ? size : r.size();
        JSONObject json = new JSONObject();
        json.put(FLAG_KEYS, StringUtils.isEmpty(condition) ? r : getResult(begin, end, r));
        json.put(FLAG_PAGE_SIZE, getPageSize(size));
        json.put(FLAG_KEY_SIZE, size);
        return json.toJSONString();
    }

    private int[] getIndex(int next) {
        next = next <= 1 ? 1 : next;
        return new int[]{(next - 1) * PAGESIZE, (next) * PAGESIZE};
    }

    private List<String> getResult(int begin, int end, List<String> r) {
        int size = r.size();
        if (begin >= size) {
            begin = size;
        }

        if (end >= size) {
            end = size;
        }
        return r.subList(begin, end);
    }

    public void setCaches(Map<String, Cache> caches) {
        this.caches = caches;
    }

    private int getPageSize(int keysSize) {
        return keysSize % PAGESIZE == 0 ? keysSize / PAGESIZE : keysSize / PAGESIZE + 1;
    }
}
