package com.feit.feep.dbms.entity.datasource;

/**
 * 数据源类型
 * Created by zhanggang on 2015/7/2.
 */
public enum DataSourceType {
    DEFAULT(0), EXTENDS(1);

    private int type;

    DataSourceType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
