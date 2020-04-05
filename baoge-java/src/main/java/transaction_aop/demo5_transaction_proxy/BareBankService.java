package transaction_aop.demo5_transaction_proxy;

import transaction_aop.BankService;
import transaction_aop.demo3_transaction_holder.ConnectionHolderBankDao;
import transaction_aop.demo3_transaction_holder.ConnectionHolderInsuranceDao;

import javax.sql.DataSource;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2018/10/4
 */
public class BareBankService implements BankService {

    private ConnectionHolderBankDao connectionHolderBankDao;
    private ConnectionHolderInsuranceDao connectionHolderInsuranceDao;

    public BareBankService(DataSource dataSource) {
        connectionHolderBankDao = new ConnectionHolderBankDao(dataSource);
        connectionHolderInsuranceDao = new ConnectionHolderInsuranceDao(dataSource);
    }

    public void transfer(final int fromId, final int toId, final int amount) {
        try {
            connectionHolderBankDao.withdraw(fromId, amount);
            connectionHolderInsuranceDao.deposit(toId, amount);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
