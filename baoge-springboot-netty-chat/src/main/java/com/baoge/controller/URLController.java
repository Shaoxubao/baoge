package com.baoge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/23
 */

@Controller
public class URLController {

    @RequestMapping("/")
    public String WebsocketChatClient() {
        return "/WebsocketChatClient";
    }
}
