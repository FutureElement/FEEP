package com.feit.feep.dbms.entity.module;

import java.io.Serializable;
import java.util.List;

/**
 * 数据表-数据模型关联表
 * Created by ZhangGang on 2015/6/8 0008.
 */
public class FeepTableModuleRelation implements Serializable {

    private static final long serialVersionUID = -7371334700650505953L;

    private String id;
    /*模型id*/
    private String moduleid;
    /*关联数据表id*/
    private String tableid;
    /*字段关联关系*/
    private List<Relation> relations;

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

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }
}
