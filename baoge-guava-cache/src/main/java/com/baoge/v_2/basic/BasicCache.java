package com.baoge.v_2.basic;

import com.baoge.common.CacheConstants;
import com.baoge.v_2.guava.SimpleGuavaCache;
import com.baoge.v_2.load.CacheLoaderImpl;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class BasicCache {

    @Autowired
    private SimpleGuavaCache simpleGuavaCache;

    @Autowired
    private LoadingCache loadingCache;

    private Timer t = new Timer();

    // <key, cacheType>
    private final Map<String, String> whiteList = new ConcurrentHashMap<>();

    public BasicCache() {
        clean();
    }

    public Object getValue(String key) {
        try {
            if (!check(key)) return null;

            Object obj = simpleGuavaCache.get(CacheConstants.CACHE_NIL, key);
            if (obj != null) return obj; // 空值检查

            obj = loadingCache.get(key);

            return obj;
        } catch (Exception e) {
            return null;
        }
    }

    public void clean() {
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Map<Object, String> map = CacheLoaderImpl.KEY_TO_BE_REMOVED;
                if (map.isEmpty()) return;
                Set<Object> set = map.keySet();

                for (Object key : set) {
                    try {
                        loadingCache.invalidate(key);
                        map.remove(key);
                    } catch (Exception e) {
                        log.error("key: " + key, e);
                    }
                }
            }
        }, 10000, 3000);
    }

    public void setValue(Object key, Object value) {
        setValue(CacheConstants.CACHE_DEFAULT, key, value);
    }

    public void setValue(String cacheName, Object key, Object value) {
        if (key == null || value == null || !check(key)) return;
        simpleGuavaCache.set(cacheName, key, value);
    }

    public void del(String key) {
        if (key == null || key.length() <= 0) return;
        try {
            loadingCache.invalidate(key);
        } catch (Exception e) {
            log.error(key, e);
        }
    }

    public boolean check(Object key) {
        if (whiteList.containsKey(key)) return true;

        String key_prefix = CacheLoaderImpl.getKeyPrefix((String) key);
        if (key_prefix == null) return false;

        return CacheLoaderImpl.KEY_METHOD_TMP.containsKey(key_prefix);
    }

    public SimpleGuavaCache getSimpleGuavaCache() {
        return simpleGuavaCache;
    }
}
