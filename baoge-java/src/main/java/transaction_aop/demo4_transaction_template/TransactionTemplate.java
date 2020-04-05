package transaction_aop.demo4_transaction_template;

import transaction_aop.demo3_transaction_holder.TransactionManager;

import javax.sql.DataSource;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2018/10/4
 * Describe: Template模式大家应该都很熟悉，比如Spring就提供了许多Template，像JdbcTemplate和JmsTemplate等。
 *           Template模式的基本思想是：在超类里将完成核心功能的方法声明为抽象方法，留给子类去实现，
 *           而在超类中完成一些通用操作，比如JMS的Session的建立和数据库事务的准备工作等。
 */
public abstract class TransactionTemplate {

    private TransactionManager transactionManager;

    protected TransactionTemplate(DataSource dataSource) {
        transactionManager = new TransactionManager(dataSource);
    }

    public void doJobInTransaction() {

        try {
            transactionManager.start();
            doJob();
            transactionManager.commit();
        } catch (Exception e) {
            transactionManager.rollback();
        } finally {
            transactionManager.close();
        }
    }

    protected abstract void doJob() throws Exception;

}
