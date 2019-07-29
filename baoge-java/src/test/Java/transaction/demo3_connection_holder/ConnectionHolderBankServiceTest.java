package transaction.demo3_connection_holder;

import org.junit.Test;
import transaction.BankFixture;
import transaction.demo3_transaction_holder.ConnectionHolderBankService;

import static junit.framework.Assert.assertEquals;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2018/10/4
 */
public class ConnectionHolderBankServiceTest extends BankFixture {

    @Test
    public void transferSuccess() throws Exception
    {
        ConnectionHolderBankService connectionHolderBankService = new ConnectionHolderBankService(dataSource);
        connectionHolderBankService.transfer(1111, 2222, 200);

        assertEquals(800, getBankAmount(1111));
        assertEquals(1200, getInsuranceAmount(2222));

    }

    @Test
    public void transferFailure() throws Exception
    {
        ConnectionHolderBankService connectionHolderBankService = new ConnectionHolderBankService(dataSource);

        int toNonExistId = 3333;
        connectionHolderBankService.transfer(1111, toNonExistId, 200);

        assertEquals(1000, getBankAmount(1111));
        assertEquals(1000, getInsuranceAmount(2222));

    }

}
