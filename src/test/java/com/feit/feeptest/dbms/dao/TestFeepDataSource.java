package com.feit.feeptest.dbms.dao;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.core.Global;
import com.feit.feep.dbms.dao.IFeepDataSourceDao;
import com.feit.feep.dbms.entity.datasource.DataSourceType;
import com.feit.feep.dbms.entity.datasource.Dialect;
import com.feit.feep.dbms.entity.module.FeepDataSource;
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
public class TestFeepDataSource extends FeepJUnit {
    @Autowired
    private IFeepDataSourceDao dataSourceDao;

    @Test
    public void test() throws Exception {
        FeepDataSource feepDataSource = new FeepDataSource(null, "feep", "feep", Dialect.POSTGRESQL.getDbtype(), "localhost", "5432", "postgres", "123456", "feep", 0, DataSourceType.DEFAULT.getType());
        String id = dataSourceDao.addDataSource(feepDataSource);
        FeepDataSource feepDataSource1 = dataSourceDao.findDataSourceById(id);
        FeepDataSource feepDataSource2 = dataSourceDao.findDataSourceByName("feep");
        Assert.assertEquals(feepDataSource1.getDialect(), feepDataSource2.getDialect());
        feepDataSource2.setShowname("default");
        boolean ret = dataSourceDao.updateDataSource(feepDataSource2);
        Assert.assertTrue(ret);
        List<FeepDataSource> dataSourceList = dataSourceDao.findAll();
        Global.getInstance().logInfo(FeepJsonUtil.toJson(dataSourceList));
        JdbcTemplate jdbcTemplate = DataSourceUtil.getJdbcTemplate(feepDataSource2);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from " + FeepDataSource.TABLE_NAME);
        int count = 0;
        while (rowSet.next()) {
            count++;
        }
        Assert.assertEquals(dataSourceList.size(), count);
        boolean del = dataSourceDao.deleteDataSource(id);
        Assert.assertTrue(del);
    }

}
