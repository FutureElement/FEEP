package com.feit.feep.dbms.entity.module;

/**
 * ���ݱ�-����ģ�͹�����
 * Created by ZhangGang on 2015/6/8 0008.
 */
public class FeepTableModuleRelation {
    private String id;
    /*���ݱ�id*/
    private String tableid;
    /*ģ��id*/
    private String moduleid;
    /*��ϵ����*/
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
