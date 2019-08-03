package com.baoge.transaction_spring;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/8/3
 */
public interface BookShopService {

    /**
     * 购买书籍
     * @param userId
     * @param isbn
     */
    void purchase(Integer userId, String isbn);

}
