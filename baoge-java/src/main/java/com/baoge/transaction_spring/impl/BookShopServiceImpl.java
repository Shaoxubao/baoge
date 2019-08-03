package com.baoge.transaction_spring.impl;

import com.baoge.transaction_spring.AccountService;
import com.baoge.transaction_spring.BookShopService;
import com.baoge.transaction_spring.dao.BookDao;
import com.baoge.transaction_spring.dao.BookStockDao;
import entity.Account;
import entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/8/3
 * @Transactional注解
 * 	该注解可以添加到类上，也可以添加到方法上
 * 	如果添加到类上，那么类中所有的方法都添加上了事务
 * 	如果添加到方法上，只有添加了该注解的方法才添加了事务
 */

//@Transactional
@Service
public class BookShopServiceImpl implements BookShopService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookStockDao bookStockDao;

    @Autowired
    private AccountService accountService;

    /**
     * 购买书籍
     *
     *事务的属性：
     * 	1.★propagation：用来设置事务的传播行为
     * 		事务的传播行为：一个方法运行在了一个开启了事务的方法中时，当前方法是使用原来的事务还是开启一个新的事务
     * 		-Propagation.REQUIRED：默认值，使用原来的事务
     * 		-Propagation.REQUIRES_NEW：将原来的事务挂起，开启一个新的事务
     * 	2.★isolation：用来设置事务的隔离级别
     * 		-Isolation.REPEATABLE_READ：可重复读，MySQL默认的隔离级别
     * 		-Isolation.READ_COMMITTED：读已提交，Oracle默认的隔离级别，开发时通常使用的隔离级别
     */
    @Transactional
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void purchase(Integer userId, String isbn) {
        // 校验参数
        if (userId == null || isbn == null) {
            return;
        }

        // 获取图书价格
        Book book = bookDao.selectByIsbn(isbn);

        // 更新图书库存
        bookStockDao.updateBookStock(isbn);

        // 更新账户余额
        Account account = new Account();
        account.setId(userId);
        account.setBalance(book.getPrice());
        accountService.updateAccount(account);

        System.out.println("purchase end.");
    }
}
