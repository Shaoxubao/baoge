package transaction.demo1_failure;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2018/10/3
 */
public class FailureInsuranceDao {

    private DataSource dataSource;

    public FailureInsuranceDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void deposit(int insuranceId, int amount) throws Exception {
        Connection connection = dataSource.getConnection();
        PreparedStatement selectStatement = connection.prepareStatement("SELECT INSURANCE_AMOUNT FROM INSURANCE_ACCOUNT WHERE INSURANCE_ID = ?");
        selectStatement.setInt(1, insuranceId);
        ResultSet resultSet = selectStatement.executeQuery();
        resultSet.next();
        int previousAmount = resultSet.getInt(1);
        resultSet.close();
        selectStatement.close();


        int newAmount = previousAmount + amount;
        PreparedStatement updateStatement = connection.prepareStatement("UPDATE INSURANCE_ACCOUNT SET INSURANCE_AMOUNT = ? WHERE INSURANCE_ID = ?");
        updateStatement.setInt(1, newAmount);
        updateStatement.setInt(2, insuranceId);
        updateStatement.execute();

        updateStatement.close();
        connection.close();
    }

}
