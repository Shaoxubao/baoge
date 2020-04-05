package transaction_aop.demo1_failure;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2018/10/3
 */
public class FailureBankDao {

    private DataSource dataSource;

    public FailureBankDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void withdraw(int bankId, int amount) throws Exception {
        Connection connection = dataSource.getConnection();
        PreparedStatement selectStatement = connection.prepareStatement("SELECT BANK_AMOUNT FROM BANK_ACCOUNT WHERE BANK_ID = ?");
        selectStatement.setInt(1, bankId);

        ResultSet resultSet = selectStatement.executeQuery();
        resultSet.next();
        int previousAmount = resultSet.getInt(1);
        resultSet.close();
        selectStatement.close();

        int newAmount = previousAmount - amount;
        PreparedStatement updateStatement = connection.prepareStatement("UPDATE BANK_ACCOUNT SET BANK_AMOUNT = ? WHERE BANK_ID = ?");
        updateStatement.setInt(1, newAmount);
        updateStatement.setInt(2, bankId);
        updateStatement.execute();

        updateStatement.close();
        connection.close();

    }

}
