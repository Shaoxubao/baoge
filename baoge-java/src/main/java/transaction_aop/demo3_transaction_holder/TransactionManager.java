package transaction_aop.demo3_transaction_holder;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2018/10/4
 */
public class TransactionManager {

    private DataSource dataSource;

    public TransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public final void start() throws Exception {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
    }

    public final void commit() throws Exception {
        Connection connection = getConnection();
        connection.commit();
    }

    public final void rollback() {
        Connection connection = null;
        try
        {
            connection = getConnection();
            connection.rollback();

        } catch (Exception e) {
            throw new RuntimeException("Couldn't rollback on connection[" + connection + "].", e);
        }
    }

    public final void close() {
        Connection connection = null;
        try
        {
            connection = getConnection();
            connection.setAutoCommit(true);
            connection.setReadOnly(false);
            connection.close();
            SingleThreadConnectionHolder.removeConnection(dataSource);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't close connection[" + connection + "].", e);
        }
    }

    private Connection getConnection() throws Exception {
        return SingleThreadConnectionHolder.getConnection(dataSource);
    }

}
