package com.feit.feep.config.spring;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.feit.feep.dbms.util.DataSourceUtil;
import com.feit.feep.dbms.util.MultiDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

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
        DataSource dataSource = DataSourceUtil.getDataSource(dbInfo);
        MultiDataSource.getInstance().addDataSource(MultiDataSource.DEFAULT, dataSource);
        return dataSource;
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate() {
        return DataSourceUtil.getJdbcTemplate(ctx.getBean("dataSource", DataSource.class));
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager() {
        return DataSourceUtil.getDataSourceTransactionManager(ctx.getBean("dataSource", DataSource.class));
    }

    @Bean(name = "transactionTemplate")
    public TransactionTemplate transactionTemplate() {
        return DataSourceUtil.getTransactionTemplate(ctx.getBean("transactionManager", DataSourceTransactionManager.class));
    }
}
