package com.feit.feep.dbms.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

import com.feit.feep.dbms.build.GeneratorSqlBuild;
import com.feit.feep.dbms.crud.FeepDao;
import com.feit.feep.dbms.crud.Retrieve;
import com.feit.feep.dbms.entity.EntityBean;
import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.query.Condition;
import com.feit.feep.dbms.entity.query.Page;
import com.feit.feep.dbms.entity.query.QueryParameter;
import com.feit.feep.exception.dbms.QueryException;

public class DefaultDao extends FeepDao implements Retrieve {

    private final static String PK_FIELDNAME = "id";

    @Autowired
    private JdbcTemplate        jdbcTemplate;

    @Override
    public EntityBean queryDataById(String id) throws QueryException {
        feepQueryBean.getQueryParameters().add(new QueryParameter(PK_FIELDNAME, id, Condition.EQUALS));
        return queryFirstData();
    }

    @Override
    public EntityBean queryFirstData() throws QueryException {
        String sql = GeneratorSqlBuild.buildQuerySqlWithoutPage(feepQueryBean.getModuleName(),
                                                                feepQueryBean.getQueryParameters(),
                                                                feepQueryBean.getFields(),
                                                                feepQueryBean.getSortFields());
        return queryFirstDataBySql(sql);
    }

    @Override
    public EntityBeanSet queryList() throws QueryException {
        String countSql = GeneratorSqlBuild.countSqlBuild(feepQueryBean.getModuleName());
        SqlRowSet countResult = jdbcTemplate.queryForRowSet(countSql);
        int totalCount = convertRowToBean(countResult).getInt(GeneratorSqlBuild.KEY_COUNT);
        Page page = new Page();
        page.setTotalCount(totalCount);
        page.setPageIndex(feepQueryBean.getPageIndex());
        page.setPageSize(feepQueryBean.getPageSize());
        page.setTotalPageNum(totalCount / feepQueryBean.getPageSize() + 1);
        String sql = GeneratorSqlBuild.buildQuerySql(feepQueryBean.getModuleName(),
                                                     feepQueryBean.getQueryParameters(),
                                                     feepQueryBean.getFields(),
                                                     feepQueryBean.getSortFields(),
                                                     page);
        EntityBeanSet ebs = queryListBySql(sql);
        ebs.setPage(page);
        ebs.setModuleName(feepQueryBean.getModuleName());
        return ebs;
    }

    @Override
    public EntityBeanSet queryListWithoutPages() throws QueryException {
        String sql = GeneratorSqlBuild.buildQuerySqlWithoutPage(feepQueryBean.getModuleName(),
                                                                feepQueryBean.getQueryParameters(),
                                                                feepQueryBean.getFields(),
                                                                feepQueryBean.getSortFields());
        return queryListBySql(sql);
    }

    @Override
    public EntityBeanSet queryListBySql(String sql) throws QueryException {
        EntityBeanSet ebs = new EntityBeanSet();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        if (null != rowSet) {
            while (rowSet.next()) {
                ebs.add(convertRowToBean(rowSet));
            }
        }
        return ebs;
    }

    @Override
    public EntityBean queryFirstDataBySql(String sql) throws QueryException {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        if (null != rowSet) {
            while (rowSet.next()) {
                return convertRowToBean(rowSet);
            }
        }
        return null;
    }

    private EntityBean convertRowToBean(SqlRowSet rowSet) throws QueryException {
        EntityBean bean = new EntityBean();
        SqlRowSetMetaData metaData = rowSet.getMetaData();
        String[] names = metaData.getColumnNames();
        for (String key : names) {
            bean.set(key, rowSet.getObject(key));
        }
        return bean;
    }
}
