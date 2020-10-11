package com.baoge.service;

import com.baoge.entity.User;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/10/11
 */
public interface UserService {

    User findUserById(Integer id);

    User updateUser(User user);

    void deleteUser(Integer id);
}
