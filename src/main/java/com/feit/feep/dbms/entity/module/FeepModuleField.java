package com.feit.feep.dbms.entity.module;

import java.io.Serializable;

/**
 * 模型属性
 * Created by ZhangGang on 2015/6/8 0008.
 */
public class FeepModuleField implements Serializable {

    private static final long serialVersionUID = -6774907351153466357L;

    private String id;
    /*模型字段名称*/
    private String name;
    /*显示名称*/
    private String showname;
    /*字典名称*/
    private String code;
    /*排序*/
    private int sort;
    /*是否为查询条件*/
    private int searchable;
    /*模型id*/
    private String moduleid;
    /*数据表字段*/
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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getTablefieldid() {
        return tablefieldid;
    }

    public void setTablefieldid(String tablefieldid) {
        this.tablefieldid = tablefieldid;
    }
}
