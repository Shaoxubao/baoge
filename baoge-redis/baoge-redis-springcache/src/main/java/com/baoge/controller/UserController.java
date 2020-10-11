package com.baoge.controller;

import com.baoge.entity.User;
import com.baoge.entity.vo.UserVO;
import com.baoge.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/10/12
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public UserVO find(@PathVariable Integer id) {
        User user = userService.findUserById(id);
        UserVO result = new UserVO();
        BeanUtils.copyProperties(user, result);

        return result;
    }

    @RequestMapping(value = "/update")
    public void update(@RequestBody UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        userService.updateUser(user);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public void delete(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

}
