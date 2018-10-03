package transaction;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2018/10/3
 */
public class DataSourceFactory {

    public static final BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUsername("BG");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:hsqldb:mem:bank");
    }

    public static DataSource createDataSource() {
        return dataSource;
    }

}
