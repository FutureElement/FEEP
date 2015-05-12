package com.feit.feep.dbms.crud.impl;

import com.feit.feep.dbms.entity.EntityBean;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.exception.dbms.QueryException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

/**
 * Created by zhanggang on 2015/5/12.
 */
public class DefaultCrud {

    protected final static String PrimaryKey = "id";

    protected FeepQueryBean feepQueryBean;

    public void setQueryBean(FeepQueryBean feepQueryBean) {
        this.feepQueryBean = feepQueryBean;
    }

    protected EntityBean convertRowToBean(SqlRowSet rowSet) throws QueryException {
        EntityBean bean = new EntityBean();
        SqlRowSetMetaData metaData = rowSet.getMetaData();
        String[] names = metaData.getColumnNames();
        for (String key : names) {
            bean.set(key, rowSet.getObject(key));
        }
        return bean;
    }
}
