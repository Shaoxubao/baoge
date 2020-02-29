package com.baoge.cache.v_2.controller;

import com.baoge.common.Result;
import com.baoge.cache.v_2.basic.BasicCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shaoxubao
 * @Date 2020/2/27 9:27
 */

@RestController
@RequestMapping("cache_v2")
public class GuavaCacheController {

    @Autowired
    private BasicCache basicCache;

    @RequestMapping("get")
    public Result<Object> getValue(String key) {

        Object value  = basicCache.getSimpleGuavaCache().getCache("cache_default").getIfPresent(key);

        return Result.success(value);
    }

    @RequestMapping("set")
    public Result<String> setValue(String key, String value) {

        basicCache.setValue(key, value);

        return Result.success();
    }

    @RequestMapping("setByCacheName")
    public Result<String> setValueByCacheName(String cacheName, String key, String value) {

        basicCache.setValue(cacheName, key, value);

        return Result.success();
    }

    @RequestMapping("getByCacheName")
    public Result<Object> getValueByCacheName(String cacheName, String key) {

        Object value  = basicCache.getSimpleGuavaCache().getCache(cacheName).getIfPresent(key);

        return Result.success(value);
    }

}
