package com.feit.feep.dbms.entity.module;

import java.io.Serializable;

/**
 * 数据表字段
 * Created by ZhangGang on 2015/6/8 0008.
 */
public class FeepTableField implements Serializable {

    private static final long serialVersionUID = 4317324852623597474L;

    private String id;
    /*字段名称*/
    private String name;
    /*显示名*/
    private String showname;
    /*物理表名*/
    private String datatype;
    /*范围*/
    private int range;
    /*精度*/
    private int precision;
    /*是否非空*/
    private int notnull;
    /*是否唯一*/
    private int unique;
    /*数据表id*/
    private String tableid;

    public String getTableid() {
        return tableid;
    }

    public void setTableid(String tableid) {
        this.tableid = tableid;
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

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getNotnull() {
        return notnull;
    }

    public void setNotnull(int notnull) {
        this.notnull = notnull;
    }

    public int getUnique() {
        return unique;
    }

    public void setUnique(int unique) {
        this.unique = unique;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }
}
