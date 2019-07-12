package com.baoge.springboot.controller;

import com.baoge.springboot.annotation.AccessLimit;
import com.baoge.springboot.annotation.ApiIdempotent;
import com.baoge.springboot.common.ServerResponse;
import com.baoge.springboot.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @ApiIdempotent
    @PostMapping("testIdempotence")
    public ServerResponse testIdempotence() {
        return testService.testIdempotence();
    }

    @AccessLimit(maxCount = 5, seconds = 5)
    @PostMapping("accessLimit")
    public ServerResponse accessLimit() {
        return testService.accessLimit();
    }

}
