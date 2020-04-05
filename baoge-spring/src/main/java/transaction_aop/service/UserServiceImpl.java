package transaction_aop.service;

import transaction_aop.annotation.MyTransactional;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/4/5
 */

@MyTransactional
public class UserServiceImpl implements UserService {
    @Override
    public void getUser() {
        System.out.println("UserServiceImpl getUser 执行...");
    }
}
