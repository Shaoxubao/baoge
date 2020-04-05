package transaction_aop.demo1_failure;

import org.junit.Test;
import transaction.demo1_failure.FailureBankDao;
import transaction.demo1_failure.FailureBankService;
import transaction.demo1_failure.FailureInsuranceDao;
import transaction_aop.BankFixture;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2018/10/4
 */
public class FailureBankServiceTest extends BankFixture {

    @Test
    public void transferSuccess() throws SQLException
    {
        FailureBankDao failureBankDao = new FailureBankDao(dataSource);
        FailureInsuranceDao failureInsuranceDao = new FailureInsuranceDao(dataSource);

        FailureBankService bankService = new FailureBankService(dataSource);
        bankService.setFailureBankDao(failureBankDao);
        bankService.setFailureInsuranceDao(failureInsuranceDao);

        bankService.transfer(1111, 2222, 200);

        assertEquals(800, getBankAmount(1111));
        assertEquals(1200, getInsuranceAmount(2222));

    }

    @Test
    public void transferFailure() throws SQLException
    {
        FailureBankDao failureBankDao = new FailureBankDao(dataSource);
        FailureInsuranceDao failureInsuranceDao = new FailureInsuranceDao(dataSource);

        FailureBankService bankService = new FailureBankService(dataSource);
        bankService.setFailureBankDao(failureBankDao);
        bankService.setFailureInsuranceDao(failureInsuranceDao);

        int toNonExistId = 3333;
        bankService.transfer(1111, toNonExistId, 200);

        assertEquals(1000, getInsuranceAmount(2222));
        assertEquals(800, getBankAmount(1111));
    }

}
