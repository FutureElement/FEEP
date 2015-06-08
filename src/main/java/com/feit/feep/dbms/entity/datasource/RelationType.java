package com.feit.feep.dbms.entity.datasource;

/**
 * Created by ZhangGang on 2015/6/8 0008.
 */
public enum RelationType {
    ONE2ONE(0), ONE2MANY(1);

    private int typeid;

    RelationType(int typeid) {
        this.typeid = typeid;
    }

    public int getTypeid() {
        return typeid;
    }

    public RelationType get(int dbtype) {
        RelationType[] relationTypes = RelationType.values();
        for (RelationType relationType : relationTypes) {
            if (relationType.getTypeid() == typeid) {
                return relationType;
            }
        }
        return null;
    }
}
