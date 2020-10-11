package com.baoge.service.impl;

import com.baoge.entity.User;
import com.baoge.mapper.UserMapper;
import com.baoge.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/10/12
 */

@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Cacheable(key = "#id")
    @Override
    public User findUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @CachePut(key = "#user.id")
    @Override
    public User updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);

        return userMapper.selectByPrimaryKey(user.getId());
    }

    @CacheEvict(key = "#id")
    @Override
    public void deleteUser(Integer id) {
        User user = new User();
        user.setId(id);
        user.setIsDeleted(1);
        userMapper.updateByPrimaryKeySelective(user);
    }
}
