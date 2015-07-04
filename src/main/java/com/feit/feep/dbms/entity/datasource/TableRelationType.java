package com.feit.feep.dbms.entity.datasource;

/**
 * Created by zhanggang on 2015/7/3.
 */
public enum TableRelationType {
    mainTable(0), subTable(1);

    private int relationType;

    TableRelationType(int relationType) {
        this.relationType = relationType;
    }

    public static TableRelationType parseRelation(int type) {
        TableRelationType[] relations = TableRelationType.values();
        for (TableRelationType relation : relations) {
            if (relation.getRelationType() == type) {
                return relation;
            }
        }
        return null;
    }

    public int getRelationType() {
        return relationType;
    }
}
