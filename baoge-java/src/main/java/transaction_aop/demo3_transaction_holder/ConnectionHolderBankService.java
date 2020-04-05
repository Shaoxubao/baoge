package transaction_aop.demo3_transaction_holder;

import transaction_aop.BankService;

import javax.sql.DataSource;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2018/10/4
 */
public class ConnectionHolderBankService implements BankService {

    private TransactionManager transactionManager;
    private ConnectionHolderBankDao connectionHolderBankDao;
    private ConnectionHolderInsuranceDao connectionHolderInsuranceDao;

    public ConnectionHolderBankService(DataSource dataSource) {
        this.transactionManager = new TransactionManager(dataSource);
        this.connectionHolderBankDao = new ConnectionHolderBankDao(dataSource);
        this.connectionHolderInsuranceDao = new ConnectionHolderInsuranceDao(dataSource);
    }


    public void transfer(int fromId, int toId, int amount) {
        try {
            transactionManager.start();
            connectionHolderBankDao.withdraw(fromId, amount);
            connectionHolderInsuranceDao.deposit(toId, amount);
            transactionManager.commit();
        } catch (Exception e) {
            transactionManager.rollback();
        } finally {
            transactionManager.close();
        }

    }
}
