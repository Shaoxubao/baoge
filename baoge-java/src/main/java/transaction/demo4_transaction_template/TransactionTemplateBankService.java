package transaction.demo4_transaction_template;

import transaction.BankService;
import transaction.demo3_transaction_holder.ConnectionHolderBankDao;
import transaction.demo3_transaction_holder.ConnectionHolderInsuranceDao;

import javax.sql.DataSource;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2018/10/4
 */
public class TransactionTemplateBankService implements BankService {

    private DataSource dataSource;
    private ConnectionHolderBankDao connectionHolderBankDao;
    private ConnectionHolderInsuranceDao connectionHolderInsuranceDao;

    public TransactionTemplateBankService(DataSource dataSource) {
        this.dataSource = dataSource;
        connectionHolderBankDao = new ConnectionHolderBankDao(dataSource);
        connectionHolderInsuranceDao = new ConnectionHolderInsuranceDao(dataSource);
    }

    public void transfer(final int fromId, final int toId, final int amount) {
        new TransactionTemplate(dataSource) {
            @Override
            protected void doJob() throws Exception {
                connectionHolderBankDao.withdraw(fromId, amount);
                connectionHolderInsuranceDao.deposit(toId, amount);
            }
        }.doJobInTransaction();
    }

}
