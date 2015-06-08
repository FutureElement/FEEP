package com.feit.feep.dbms.entity.module;

/**
 * 数据表-数据模型关联表
 * Created by ZhangGang on 2015/6/8 0008.
 */
public class FeepTableModuleRelation {
    private String id;
    /*数据表id*/
    private String tableid;
    /*模型id*/
    private String moduleid;
    /*关系类型*/
    private int relationtype;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableid() {
        return tableid;
    }

    public void setTableid(String tableid) {
        this.tableid = tableid;
    }

    public String getModuleid() {
        return moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
    }

    public int getRelationtype() {
        return relationtype;
    }

    public void setRelationtype(int relationtype) {
        this.relationtype = relationtype;
    }
}
