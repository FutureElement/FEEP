package com.feit.feep.dbms.entity.module;

import java.io.Serializable;
import java.util.List;

/**
 * 数据模型与表关联
 * Created by ZhangGang on 2015/6/8 0008.
 */
public class FeepTableModuleRelation implements Serializable {

    private static final long serialVersionUID = -7371334700650505953L;

    private String id;
    /*模型id*/
    private String moduleid;
    /*关联数据表id*/
    private String tableid;
    /*关联关系*/
    private int relationType;
    /*关系表类型*/
    private int tableType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModuleid() {
        return moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
    }

    public String getTableid() {
        return tableid;
    }

    public void setTableid(String tableid) {
        this.tableid = tableid;
    }

    public int getRelationType() {
        return relationType;
    }

    public void setRelationType(int relationType) {
        this.relationType = relationType;
    }

    public int getTableType() {
        return tableType;
    }

    public void setTableType(int tableType) {
        this.tableType = tableType;
    }
}
