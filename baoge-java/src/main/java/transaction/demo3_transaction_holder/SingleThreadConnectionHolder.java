package transaction.demo3_transaction_holder;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2018/10/4
 * Describe: 为了获得线程安全的ConnectionHolder类，我们可以引入Java提供的ThreadLocal类，该类保证一个类的实例变量在各个线程中都有一份单独的拷贝，
 *           从而不会影响其他线程中的实例变量。
 */
public class SingleThreadConnectionHolder {

    private static ThreadLocal<ConnectionHolder> localConnectionHolder = new ThreadLocal<ConnectionHolder>();

    public static Connection getConnection(DataSource dataSource) throws Exception {
        return getConnectionHolder().getConnection(dataSource);
    }

    public static void removeConnection(DataSource dataSource) {
        getConnectionHolder().removeConnection(dataSource);
    }

    private static ConnectionHolder getConnectionHolder() {
        ConnectionHolder connectionHolder = localConnectionHolder.get();
        if (connectionHolder == null) {
            connectionHolder = new ConnectionHolder();
            localConnectionHolder.set(connectionHolder);
        }
        return connectionHolder;
    }

}
