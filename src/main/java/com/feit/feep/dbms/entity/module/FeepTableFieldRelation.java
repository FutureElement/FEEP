package com.feit.feep.dbms.entity.module;

import java.io.Serializable;

/**
 * 数据模型字段关联
 * Created by zhanggang on 2015/7/3.
 */
public class FeepTableFieldRelation implements Serializable {

    private static final long serialVersionUID = 7907099206220706539L;

    public static final String fk = "tablemodulerelationid";

    private String id;
    /*模型关联id*/
    private String tablemodulerelationid;
    /*主模型字段id*/
    private String mainmodulefieldid;
    /*从表字段id*/
    private String subtablefieldid;
    /*约束条件*/
    private String condition;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTablemodulerelationid() {
        return tablemodulerelationid;
    }

    public void setTablemodulerelationid(String tablemodulerelationid) {
        this.tablemodulerelationid = tablemodulerelationid;
    }

    public String getMainmodulefieldid() {
        return mainmodulefieldid;
    }

    public void setMainmodulefieldid(String mainmodulefieldid) {
        this.mainmodulefieldid = mainmodulefieldid;
    }

    public String getSubtablefieldid() {
        return subtablefieldid;
    }

    public void setSubtablefieldid(String subtablefieldid) {
        this.subtablefieldid = subtablefieldid;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
