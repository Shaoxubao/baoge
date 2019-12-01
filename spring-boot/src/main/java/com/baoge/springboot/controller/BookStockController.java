package com.baoge.springboot.controller;

import com.baoge.springboot.jdbc.service.BookStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/1
 */

@RestController
public class BookStockController {

    @Autowired
    private BookStockService bookStockService;

    @ResponseBody
    @RequestMapping("/stock/op")
    public String stockOp() {

        bookStockService.stockHandle();

        return "success";
    }

}
