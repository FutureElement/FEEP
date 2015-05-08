package com.feit.feeptest.dbms;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.core.Global;
import com.feit.feep.dbms.entity.datasource.DBInfo;

public class TestJDBC extends FeepJUnit {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DBInfo       databaseConfig;

    @Test
    public void test1() {
        long s = System.currentTimeMillis();
        SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from feep_user");
        if (null != rs) {
            while (rs.next()) {
                Global.getInstance().logInfo(rs.getString("username"));
            }
        }
        long e = System.currentTimeMillis();
        Global.getInstance().logInfo(e - s);
    }

    @Test
    public void test2() {
        Global.getInstance().logInfo(databaseConfig.getUrl());
    }
}
