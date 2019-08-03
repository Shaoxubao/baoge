package transaction_spring;

import base.SpringTestBase;
import com.baoge.transaction_spring.BookShopService;
import com.baoge.transaction_spring.CashierService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/8/3
 */
public class TransactionSpringTest extends SpringTestBase {

    @Autowired
    private CashierService cashierService;

    @Autowired
    private BookShopService bookShopService;

    /**
     * 事务传播行为
     */
    @Test
    public void testTransactionSpring() {

        cashierService.checkout(1, Arrays.asList("1001", "1002"));
    }

    /**
     * 事务隔离级别
     */
    @Test
    public void testTransactionSpringIsolation() {

        bookShopService.findBook("1001");
    }

}
