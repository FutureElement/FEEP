package com.feit.feep.dbms.crud.impl;

import com.feit.feep.dbms.build.GeneratorSqlBuild;
import com.feit.feep.dbms.crud.middle.RetrieveRepository;
import com.feit.feep.dbms.entity.EntityBean;
import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.dbms.entity.query.Page;
import com.feit.feep.exception.dbms.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

/**
 * Created by zhanggang on 2015/5/12.
 */
@Repository
public class RetrieveImpl extends DefaultCrud implements RetrieveRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public EntityBean queryDataById(String id, FeepQueryBean feepQueryBean) throws QueryException {
        return null;
    }

    @Override
    public EntityBean queryFirstData(FeepQueryBean feepQueryBean) throws QueryException {
        String sql = GeneratorSqlBuild.buildQuerySqlWithoutPage(feepQueryBean.getModuleName(),
                feepQueryBean.getQueryParameters(),
                feepQueryBean.getFields(),
                feepQueryBean.getSortFields());
        return queryFirstDataBySql(sql);
    }

    @Override
    public EntityBeanSet queryList(FeepQueryBean feepQueryBean) throws QueryException {
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
    public EntityBeanSet queryListWithoutPages(FeepQueryBean feepQueryBean) throws QueryException {
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

    @Override
    public int countDate(FeepQueryBean feepQueryBean) throws QueryException {
        return 0;
    }

}
