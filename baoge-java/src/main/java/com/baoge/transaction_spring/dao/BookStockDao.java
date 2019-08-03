package com.baoge.transaction_spring.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/8/3
 */

@Repository
public class BookStockDao {

    @Autowired
    private SqlSession session;

    public Boolean updateBookStock(String isbn) {
        return session.update("BookStockMapper.updateBookStock", isbn) > 1;
    }
}
