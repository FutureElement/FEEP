package com.feit.feep.dbms.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.feit.feep.dbms.entity.datasource.DBInfo;
import com.feit.feep.dbms.entity.module.FeepDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 多数据源工具类
 * Created by zhanggang on 2015/7/2.
 */
public class DataSourceUtil {

    public static DataSource getDataSource(FeepDataSource feepDataSource) throws SQLException {
        return getDataSource(new DBInfo(feepDataSource));
    }

    public static DataSource getDataSource(DBInfo dbInfo) throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dbInfo.getUrl());
        dataSource.setUsername(dbInfo.getUsername());
        dataSource.setPassword(dbInfo.getPassword());
        dataSource.setInitialSize(dbInfo.getInitSize());
        dataSource.setMaxActive(dbInfo.getMaxActive());
        dataSource.setMinIdle(dbInfo.getInitSize());
        dataSource.setMaxWait(60000);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(33);

        dataSource.setValidationQuery("select 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);

        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(25200000);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(1800);
        dataSource.setLogAbandoned(true);
        dataSource.setFilters("wall,mergeStat");
        return dataSource;
    }

    public static JdbcTemplate getJdbcTemplate(FeepDataSource feepDataSource) throws SQLException {
        return getJdbcTemplate(getDataSource(feepDataSource));
    }

    public static JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource, false);
    }

    public static DataSourceTransactionManager getDataSourceTransactionManager(FeepDataSource feepDataSource) throws SQLException {
        return getDataSourceTransactionManager(getDataSource(feepDataSource));
    }

    public static DataSourceTransactionManager getDataSourceTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    public static TransactionTemplate getTransactionTemplate(FeepDataSource feepDataSource) throws SQLException {
        return getTransactionTemplate(getDataSource(feepDataSource));
    }

    public static TransactionTemplate getTransactionTemplate(DataSource dataSource) throws SQLException {
        return getTransactionTemplate(getDataSourceTransactionManager(dataSource));
    }

    public static TransactionTemplate getTransactionTemplate(DataSourceTransactionManager dataSourceTransactionManager) {
        return new TransactionTemplate(dataSourceTransactionManager);
    }
}
