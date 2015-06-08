package com.feit.feep.dbms.entity.module;

import java.io.Serializable;

/**
 * ģ������
 * Created by ZhangGang on 2015/6/8 0008.
 */
public class FeepModuleProperty implements Serializable {

    private static final long serialVersionUID = -6774907351153466357L;

    private String id;
    /*ģ���ֶ�����*/
    private String name;
    /*��ʾ����*/
    private String showname;
    /*�ֵ�����*/
    private String code;
    /*����*/
    private String sort;
    /*�Ƿ�Ϊ��ѯ����*/
    private int searchable;
    /*ģ��id*/
    private String moduleid;
    /*���ݱ��ֶ�*/
    private String tablefieldid;

    public int getSearchable() {
        return searchable;
    }

    public void setSearchable(int searchable) {
        this.searchable = searchable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public String getModuleid() {
        return moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTablefieldid() {
        return tablefieldid;
    }

    public void setTablefieldid(String tablefieldid) {
        this.tablefieldid = tablefieldid;
    }
}
