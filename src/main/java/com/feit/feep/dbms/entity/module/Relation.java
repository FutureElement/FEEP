package com.feit.feep.dbms.entity.module;

import com.feit.feep.dbms.entity.query.Condition;

import java.io.Serializable;

/**
 * 数据表关联
 * Created by zhanggang on 2015/7/1.
 */
public class Relation implements Serializable {

    private static final long serialVersionUID = -5707761283458089215L;

    private String mainTableField;
    private String subTableField;
    private Condition condition;

    public String getMainTableField() {
        return mainTableField;
    }

    public void setMainTableField(String mainTableField) {
        this.mainTableField = mainTableField;
    }

    public String getSubTableField() {
        return subTableField;
    }

    public void setSubTableField(String subTableField) {
        this.subTableField = subTableField;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
