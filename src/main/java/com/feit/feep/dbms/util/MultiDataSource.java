package com.feit.feep.dbms.util;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数据源配置方法
 * Created by zhanggang on 2015/7/2.
 */
public class MultiDataSource {

    public static final String DEFAULT = "default";

    private static MultiDataSource instance = new MultiDataSource();

    private Map<String, JdbcTemplate> jdbcTemplateMap;
    private Map<String, TransactionTemplate> transactionTemplateMap;
    private Map<String, DataSource> dataSourceMap;

    private MultiDataSource() {
        jdbcTemplateMap = new HashMap<String, JdbcTemplate>();
        transactionTemplateMap = new HashMap<String, TransactionTemplate>();
        dataSourceMap = new HashMap<String, javax.sql.DataSource>();
    }

    public static MultiDataSource getInstance() {
        return instance;
    }

    public JdbcTemplate getJdbcTemplate(String name) {
        return jdbcTemplateMap.get(name);
    }

    public DataSource getDataSource(String name) {
        return dataSourceMap.get(name);
    }

    public TransactionTemplate getTransactionTemplate(String name) {
        return transactionTemplateMap.get(name);
    }

    public void addJdbcTemplate(String name, JdbcTemplate jdbcTemplate) {
        if (null == jdbcTemplateMap.get(name))
            jdbcTemplateMap.put(name, jdbcTemplate);
    }

    public void addDataSource(String name, DataSource dataSource) {
        if (null == dataSourceMap.get(name))
            dataSourceMap.put(name, dataSource);
    }

    public void addTransactionTemplate(String name, TransactionTemplate transactionTemplate) {
        if (null == transactionTemplateMap.get(name))
            transactionTemplateMap.put(name, transactionTemplate);
    }
}
