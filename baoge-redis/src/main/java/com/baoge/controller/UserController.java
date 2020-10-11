package com.baoge.controller;

import com.baoge.entity.User;
import com.baoge.entity.vo.UserVO;
import com.baoge.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/10/11
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    @ResponseBody
    public void init() {
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUserName("baoge" + i);
            user.setPassword("baoge" + i);
            user.setSex(rand.nextInt(2));

            userService.createUser(user);
        }
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserVO find(@PathVariable Integer id) {

        User user = userService.findUserById(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);

        return userVO;
    }

}
