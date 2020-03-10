package com.baoge.springboot.controller;

import com.baoge.springboot.common.Result;
import com.baoge.springboot.jedis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Set;

/**
 * @Author shaoxubao
 * @Date 2020/3/10 10:13
 */

@Controller
@RequestMapping("/jedis")
public class JedisController {

    @Autowired
    private RedisService redisService;

    @GetMapping("/set")
    @ResponseBody
    public Result set() {
        redisService.set("girlfriend", "sakura");
        return Result.success();
    }

    @GetMapping("/get")
    @ResponseBody
    public Result get() {
        String str = redisService.get("girlfriend", String.class);
        return Result.success().add("girlfriend", str);
    }

    @GetMapping("/hset")
    @ResponseBody
    public Result hset() {
        redisService.hset("baoge", "1", "a");
        redisService.hset("baoge", "2", "b");
        redisService.hset("baoge", "3", "c");
        redisService.hset("baoge", "4", "d");

        String hget1 = redisService.hget("baoge", "1");
        System.out.println("hget: " + hget1);

        Map<String, String> hgetAll = redisService.hgetAll("baoge");
        System.out.println("hgetAll: " + hgetAll);

        String[] arr = new String[] {"1", "2", "3", "4"};
        redisService.hdel("baoge", arr);

        return Result.success();
    }

    @GetMapping("/zset")
    @ResponseBody
    public Result zset() {

        redisService.zadd("baoge1", 1, "a");
        redisService.zadd("baoge1", 2, "b");
        redisService.zadd("baoge1", 3, "c");
        redisService.zadd("baoge2", 1, "d");

        Long zcard = redisService.zcard("baoge1");
        System.out.println("zcard: " + zcard);

        Set<String> zrangeSet = redisService.zrange("baoge1", 0, -1);
        System.out.println("zrange: " + zrangeSet);

        Set<String> stringSet = redisService.zrevrange("baoge1", 0, -1);
        System.out.println("zrevrange: " + stringSet);

        Long zremrangeByScore = redisService.zremrangeByScore("baoge1", "1", "1");
        System.out.println("zremrangeByScore: " + zremrangeByScore);

        Set<String> stringSet2 = redisService.zrevrange("baoge1", 0, -1);
        System.out.println("zrevrange2: " + stringSet2);

        return Result.success();
    }

}
