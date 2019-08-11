package com.baoge.springbootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/8/11
 */

@Controller
public class TestController {

    @RequestMapping("/success")
    public String success(Map<String, Object> map) {
        map.put("hello", "<h1>hello kitty!</h1>");
        map.put("users", Arrays.asList("zhangsan", "lisi"));

        return "success";
    }

}
