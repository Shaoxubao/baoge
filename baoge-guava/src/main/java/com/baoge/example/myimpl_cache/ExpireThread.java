package com.baoge.example.myimpl_cache;

import java.util.concurrent.TimeUnit;

/**
 * @Author shaoxubao
 * @Date 2020/7/24 16:19
 *
 * 过期缓存检测类
 */
public class ExpireThread implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                // 每十秒检测一次
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 缓存检测和清除的方法
            expireCache();
        }
    }

    private void expireCache() {
        System.out.println("检测缓存是否过期缓存");
        for (String key : CacheGlobal.concurrentHashMap.keySet()) {
            CacheValue cacheValue = CacheGlobal.concurrentHashMap.get(key);
            long timeoutTime = System.currentTimeMillis() - cacheValue.getWriteTime();
            if (cacheValue.getExpireTime() > timeoutTime) {
                continue;
            }
            System.out.println("删除过期key：" + key);
            CacheGlobal.concurrentHashMap.remove(key);
        }
    }
}
