package com.feit.feep.config.spring;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;
import com.feit.feep.core.loader.entity.FeepConfig;
import com.feit.feep.dbms.entity.datasource.DBInfo;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class DataPersistence {
    @Autowired
    private ApplicationContext ctx;

    @Bean(name = "databaseConfig")
    public DBInfo databaseConfig() {
        return ctx.getBean("feepConfig", FeepConfig.class).getDBInfo();
    }

    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    public DataSource dataSource() throws SQLException {
        DBInfo dbInfo = ctx.getBean("databaseConfig", DBInfo.class);
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
        dataSource.setFilters("mergeStat");
        return dataSource;
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ctx.getBean("dataSource", DataSource.class), false);
        return jdbcTemplate;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(ctx.getBean("dataSource", DataSource.class));
        return transactionManager;
    }

    @Bean(name = "transactionTemplate")
    public TransactionTemplate transactionTemplate() {
        TransactionTemplate transactionTemplate = new TransactionTemplate(ctx.getBean("transactionManager", DataSourceTransactionManager.class));
        return transactionTemplate;
    }
}
