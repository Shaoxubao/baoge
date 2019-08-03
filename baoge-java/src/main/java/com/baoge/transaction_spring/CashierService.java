package com.baoge.transaction_spring;

import java.util.List;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/8/3
 */
public interface CashierService {

    /**
     * 结算
     * @param userId
     * @param isbns
     */
    void checkout(Integer userId, List<String> isbns);

}
