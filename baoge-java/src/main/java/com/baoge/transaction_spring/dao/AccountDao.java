package com.baoge.transaction_spring.dao;

import entity.Account;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/8/3
 */

@Repository
public class AccountDao {

    @Autowired
    private SqlSession session;

    public Boolean update(Account account) {
        return session.update("AccountMapper.update", account) > 0;
    }

}
