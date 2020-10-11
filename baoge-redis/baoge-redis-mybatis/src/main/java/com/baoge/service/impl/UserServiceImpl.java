package com.baoge.service.impl;

import com.baoge.entity.User;
import com.baoge.mapper.UserMapper;
import com.baoge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/10/11
 */

@Service
public class UserServiceImpl implements UserService {

    private static final String CACHE_USER_KEY = "user:";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void createUser(User user) {
        userMapper.insertSelective(user);

        String key = CACHE_USER_KEY + user.getId();
        user = userMapper.selectByPrimaryKey(user.getId());
        redisTemplate.opsForValue().set(key, user);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User findUserById(Integer userId) {
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();

        String key = CACHE_USER_KEY + userId;

        User user = valueOperations.get(key);
        if (user == null) {
            user = userMapper.selectByPrimaryKey(user);
            valueOperations.set(key, user);
        }

        return user;
    }
}
