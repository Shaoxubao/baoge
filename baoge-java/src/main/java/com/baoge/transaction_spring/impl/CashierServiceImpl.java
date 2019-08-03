package com.baoge.transaction_spring.impl;

import com.baoge.transaction_spring.BookShopService;
import com.baoge.transaction_spring.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/8/3
 */

@Service
public class CashierServiceImpl implements CashierService {


    @Autowired
    private BookShopService bookShopService;

    /**
     * 结算
     *
     * @param userId
     * @param isbnList
     */
    @Transactional
    @Override
    public void checkout(Integer userId, List<String> isbnList) {
        if (userId == null || CollectionUtils.isEmpty(isbnList)) {
            return;
        }

        for (String isbn : isbnList) {
            // 调用购买书籍方法
            bookShopService.purchase(userId, isbn);
        }

    }
}
