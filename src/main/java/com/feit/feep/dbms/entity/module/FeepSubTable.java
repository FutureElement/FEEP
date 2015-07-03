package com.feit.feep.dbms.entity.module;

import java.io.Serializable;
import java.util.List;

/**
 * 数据模型关联表信息
 * Created by zhanggang on 2015/7/3.
 */
public class FeepSubTable implements Serializable {

    private static final long serialVersionUID = -8968975863869880422L;

    /*与模型关系*/
    private FeepTableModuleRelation tableModuleRelations;
    /*查询字段*/
    private List<FeepModuleField> subFields;
    /*关联关系*/
    private List<FeepTableFieldRelation> tableFieldRelations;

    public FeepTableModuleRelation getTableModuleRelations() {
        return tableModuleRelations;
    }

    public void setTableModuleRelations(FeepTableModuleRelation tableModuleRelations) {
        this.tableModuleRelations = tableModuleRelations;
    }

    public List<FeepModuleField> getSubFields() {
        return subFields;
    }

    public void setSubFields(List<FeepModuleField> subFields) {
        this.subFields = subFields;
    }

    public List<FeepTableFieldRelation> getTableFieldRelations() {
        return tableFieldRelations;
    }

    public void setTableFieldRelations(List<FeepTableFieldRelation> tableFieldRelations) {
        this.tableFieldRelations = tableFieldRelations;
    }
}
