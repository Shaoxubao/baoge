package com.baoge.example.myimpl_cache;

/**
 * @Author shaoxubao
 * @Date 2020/7/24 16:45
 *
 * 手动实现一个简易本地缓存系统
 * 支持定时删除过期的key
 */
public class MyCacheTest {
    public static void main(String[] args) {
        CacheUtils cacheUtils = new CacheUtils();
        cacheUtils.put("key", "老王", 3);

        System.out.println(cacheUtils.get("key"));

        System.out.println(cacheUtils.get("key1"));
    }
}
