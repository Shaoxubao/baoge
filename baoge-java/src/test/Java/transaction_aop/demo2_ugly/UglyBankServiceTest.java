package transaction_aop.demo2_ugly;

import org.junit.Test;
import transaction.demo2_ugly.UglyBankDao;
import transaction.demo2_ugly.UglyBankService;
import transaction.demo2_ugly.UglyInsuranceDao;
import transaction_aop.BankFixture;
import static junit.framework.Assert.assertEquals;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2018/10/4
 */
public class UglyBankServiceTest extends BankFixture {

    @Test
    public void transferSuccess() throws Exception {
        UglyBankDao failureBankDao = new UglyBankDao();
        UglyInsuranceDao failureInsuranceDao = new UglyInsuranceDao();

        UglyBankService bankService = new UglyBankService(dataSource);
        bankService.setUglyBankDao(failureBankDao);
        bankService.setUglyInsuranceDao(failureInsuranceDao);

        bankService.transfer(1111, 2222,200);

        assertEquals(800, getBankAmount(1111));
        assertEquals(1200, getInsuranceAmount(2222));
    }

    @Test
    public void transferFailure() throws Exception {
        UglyBankDao failureBankDao = new UglyBankDao();
        UglyInsuranceDao failureInsuranceDao = new UglyInsuranceDao();

        UglyBankService bankService = new UglyBankService(dataSource);
        bankService.setUglyBankDao(failureBankDao);
        bankService.setUglyInsuranceDao(failureInsuranceDao);

        int toNonExistId = 3333;
        bankService.transfer(1111, toNonExistId,200);

        assertEquals(1000,getBankAmount(1111));
        assertEquals(1000, getInsuranceAmount(2222));
    }

}
