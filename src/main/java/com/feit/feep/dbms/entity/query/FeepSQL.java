package com.feit.feep.dbms.entity.query;

/**
 * Created by ZhangGang on 2015/6/27 0027.
 */
public class FeepSQL {

    private String sql;
    private Object[] params;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
