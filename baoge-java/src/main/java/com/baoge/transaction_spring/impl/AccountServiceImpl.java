package com.baoge.transaction_spring.impl;

import com.baoge.transaction_spring.AccountService;
import com.baoge.transaction_spring.dao.AccountDao;
import entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/8/3
 */

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    /**
     * 账户更新
     *
     * @param account
     */
    @Override
    public void updateAccount(Account account) {
        accountDao.update(account);
    }
}
