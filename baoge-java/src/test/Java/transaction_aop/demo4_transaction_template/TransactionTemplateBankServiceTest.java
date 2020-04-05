package transaction_aop.demo4_transaction_template;

import org.junit.Test;
import transaction.demo4_transaction_template.TransactionTemplateBankService;
import transaction_aop.BankFixture;

import static junit.framework.Assert.assertEquals;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2018/10/4
 */
public class TransactionTemplateBankServiceTest extends BankFixture {
    @Test
    public void transferSuccess() throws Exception {
        TransactionTemplateBankService transactionTemplateBankService = new TransactionTemplateBankService(dataSource);
        transactionTemplateBankService.transfer(1111, 2222, 200);

        assertEquals(800, getBankAmount(1111));
        assertEquals(1200, getInsuranceAmount(2222));

    }

    @Test
    public void transferFailure() throws Exception {
        TransactionTemplateBankService transactionTemplateBankService = new TransactionTemplateBankService(dataSource);

        int toNonExistId = 3333;
        transactionTemplateBankService.transfer(1111, toNonExistId, 200);

        assertEquals(1000, getBankAmount(1111));
        assertEquals(1000, getInsuranceAmount(2222));
    }

}
