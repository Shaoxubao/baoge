package transaction_aop.demo6_annotation;

import org.junit.Test;
import transaction.demo6_annotation.AnnotationBankService;
import transaction.demo6_annotation.TransactionEnabledAnnotationProxyManager;
import transaction_aop.BankFixture;
import transaction.BankService;
import transaction.demo3_transaction_holder.TransactionManager;

import static junit.framework.Assert.assertEquals;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2018/10/4
 */
public class AnnotationBankServiceTest extends BankFixture {

    @Test
    public void transferSuccess() throws Exception {
        TransactionManager transactionManager = new TransactionManager(dataSource);
        TransactionEnabledAnnotationProxyManager transactionEnabledProxyManager = new TransactionEnabledAnnotationProxyManager(transactionManager);
        BankService bankService = new AnnotationBankService(dataSource);
        BankService proxyBankService = (BankService) transactionEnabledProxyManager.proxyFor(bankService);
        proxyBankService.transfer(1111, 2222, 200);

        assertEquals(800, getBankAmount(1111));
        assertEquals(1200, getInsuranceAmount(2222));
    }

    @Test
    public void transferFailure() throws Exception {
        TransactionEnabledAnnotationProxyManager transactionEnabledAnnotationProxyManager = new TransactionEnabledAnnotationProxyManager(new TransactionManager(dataSource));
        BankService bankService = new AnnotationBankService(dataSource);
        BankService proxyBankService = (BankService) transactionEnabledAnnotationProxyManager.proxyFor(bankService);

        int toNonExistId = 3333;
        proxyBankService.transfer(1111, toNonExistId, 200);

        assertEquals(1000, getBankAmount(1111));
        assertEquals(1000, getInsuranceAmount(2222));
    }

}
