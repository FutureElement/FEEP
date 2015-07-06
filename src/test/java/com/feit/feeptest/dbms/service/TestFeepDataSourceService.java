package com.feit.feeptest.dbms.service;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.core.Global;
import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.datasource.DataSourceType;
import com.feit.feep.dbms.entity.datasource.Dialect;
import com.feit.feep.dbms.entity.module.FeepDataSource;
import com.feit.feep.dbms.service.IDataSourceService;
import com.feit.feep.dbms.util.DataSourceUtil;
import com.feit.feep.util.json.FeepJsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

/**
 * Created by ZhangGang on 2015/7/6 0006.
 */
public class TestFeepDataSourceService extends FeepJUnit {
    @Autowired
    private IDataSourceService dataSourceService;

    @Test
    public void test() throws Exception {
        FeepDataSource feepDataSource = new FeepDataSource(null, "feep", "feep", Dialect.POSTGRESQL.getDbtype(), "localhost", "5432", "postgres", "123456", "feep", 0, DataSourceType.DEFAULT.getType());
        String id = dataSourceService.addDataSource(feepDataSource);
        FeepDataSource feepDataSource1 = dataSourceService.findDataSourceById(id);
        FeepDataSource feepDataSource2 = dataSourceService.findDataSourceByName("feep");
        FeepDataSource feepDataSource3 = dataSourceService.getDefaultDataSourceInfo();
        Assert.assertEquals(feepDataSource1.getDialect(), feepDataSource2.getDialect());
        Assert.assertEquals(feepDataSource3.getDialect(), feepDataSource2.getDialect());
        feepDataSource2.setShowname("default");
        boolean ret = dataSourceService.modifyDataSource(feepDataSource2);
        Assert.assertTrue(ret);
        EntityBeanSet entityBeanSet = dataSourceService.findAllDataSource();
        Global.getInstance().logInfo(entityBeanSet.toString());
        boolean del = dataSourceService.deleteDataSource(id);
        Assert.assertTrue(del);
    }
}
