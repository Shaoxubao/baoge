package transaction.demo6_annotation;

import transaction.BankService;
import transaction.demo3_transaction_holder.ConnectionHolderBankDao;
import transaction.demo3_transaction_holder.ConnectionHolderInsuranceDao;

import javax.sql.DataSource;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2018/10/4
 */
public class AnnotationBankService implements BankService {

    private ConnectionHolderBankDao connectionHolderBankDao;
    private ConnectionHolderInsuranceDao connectionHolderInsuranceDao;

    public AnnotationBankService(DataSource dataSource) {
        connectionHolderBankDao = new ConnectionHolderBankDao(dataSource);
        connectionHolderInsuranceDao = new ConnectionHolderInsuranceDao(dataSource);
    }

    @Transactional
    public void transfer(final int fromId, final int toId, final int amount) {
        try {
            connectionHolderBankDao.withdraw(fromId, amount);
            connectionHolderInsuranceDao.deposit(toId, amount);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
