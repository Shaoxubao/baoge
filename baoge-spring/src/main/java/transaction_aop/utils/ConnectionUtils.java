package transaction_aop.utils;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/4/5
 */

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;

/**
 * 连接的工具类，它用于从数据源中获取一个连接，并且实现和线程的绑定
 */
public class ConnectionUtils {

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    private static BasicDataSource dataSource = new BasicDataSource();

    // 静态代码块,设置连接数据库的参数
    static {
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("12345678");
    }

    /**
     * 获取当前线程上的连接
     */
    public Connection getThreadConnection() {
        try{
            // 1.先从ThreadLocal上获取
            Connection conn = threadLocal.get();
            // 2.判断当前线程上是否有连接
            if (conn == null) {
                // 3.从数据源中获取一个连接，并且存入ThreadLocal中
                conn = dataSource.getConnection();
                threadLocal.set(conn);
            }
            // 4.返回当前线程上的连接
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection(){
        threadLocal.remove();
    }
}
