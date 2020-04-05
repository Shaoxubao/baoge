package transaction_aop.demo3_transaction_holder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2018/10/4
 * Describe:ConnectionHolder的工作机制是：我们将Connection对象放在一个全局公用的地方，然后在不同的操作中都从这个地方取得Connection，
 *          从而完成Connection共享的目的，这也是一种ServiceLocator模式，有点像JNDI。
 *
 *          虽然我们不会自己手动地在中途移除或者关闭Conncetion对象（当然，在事务处理末尾我们应该关闭Conncetion），我们却无法阻止其他线程这么做。
 *          比如，ConnectionHolder类是可以在多个线程中同时使用的，并且这些线程使用了同一个DataSource，其中一个线程使用完Connection后便将其关闭，
 *          而此时另外一个线程正试图使用这个Connection，问题就出来了。因此，上面的ConnectionHolder不是线程安全的。
 */
public class ConnectionHolder {

    private Map<DataSource, Connection> connectionMap = new HashMap<DataSource, Connection>();

    public Connection getConnection(DataSource dataSource) throws Exception {
        Connection connection = connectionMap.get(dataSource);
        if (connection == null || connection.isClosed()) {
            connection = dataSource.getConnection();
            connectionMap.put(dataSource, connection);
        }

        return connection;
    }

    public void removeConnection(DataSource dataSource) {
        connectionMap.remove(dataSource);
    }

}
