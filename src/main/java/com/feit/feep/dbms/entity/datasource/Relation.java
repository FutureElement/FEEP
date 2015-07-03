package com.feit.feep.dbms.entity.datasource;

/**
 * Created by zhanggang on 2015/7/3.
 */
public enum Relation {
    INNER_JOIN(0), LEFT_JOIN(1), RIGHT_JOIN(2), OUTER_JOIN(3);

    private int relationType;

    Relation(int relationType) {
        this.relationType = relationType;
    }

    public static Relation parseRelation(int type) {
        Relation[] relations = Relation.values();
        for (Relation relation : relations) {
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
