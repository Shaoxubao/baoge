package com.baoge.controller;

import com.baoge.common.Result;
import com.baoge.service.GuavaCacheService;
import com.baoge.service.cache.GuavaCacheFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shaoxubao
 * @Date 2020/2/26 15:18
 */

@RestController
@RequestMapping("cache")
public class CacheController {

    @Autowired
    private GuavaCacheFactory guavaCacheFactory;

    @Autowired
    private GuavaCacheService<String, String> guavaCacheService;

    @RequestMapping("get")
    public Result<String> get(String key) throws Exception {
        String value = guavaCacheService.getIfPresent(key);
        return Result.success(value);
    }

    @RequestMapping("put")
    public Result<String> put(String key) {
        guavaCacheService.put(key, "你好，Guava");
        return Result.success();
    }

    @RequestMapping("putByName")
    public Result<String> put1(String key, String name, String value) {
        guavaCacheFactory.buildGuavaCacheService(name).put(key, value);
        return Result.success();
    }

    @RequestMapping("getByName")
    public Result<String> get1(String key, String name) {
        String a = (String) guavaCacheFactory.getGuavaCacheService(name).getIfPresent(key);
        return Result.success(a);
    }

}
