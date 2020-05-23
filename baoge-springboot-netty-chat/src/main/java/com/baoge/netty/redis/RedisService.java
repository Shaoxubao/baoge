package com.baoge.netty.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/23
 */

@Service
public class RedisService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;

    public void saveString(String key, String value) {
        valOpsStr.set(key, value);
    }

    public String getString(String key) {
        return valOpsStr.get(key);
    }

    public void deleteString(String key) {
        stringRedisTemplate.delete(key);
    }

}
