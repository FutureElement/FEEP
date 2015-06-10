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
    /*数据类型*/
    private String datatype;
    /*范围*/
    private int range;
    /*精度*/
    private int precision;
    /*是否非空*/
    private boolean isnotnull;
    /*是否唯一*/
    private boolean isunique;
    /*数据表id*/
    private String tableid;

    public FeepTableField() {

    }

    public FeepTableField(String id, String name, String showname, String datatype, int range, int precision, boolean isnotnull, boolean isunique, String tableid) {
        this();
        this.id = id;
        this.name = name;
        this.showname = showname;
        this.datatype = datatype;
        this.range = range;
        this.precision = precision;
        this.isnotnull = isnotnull;
        this.isunique = isunique;
        this.tableid = tableid;
    }

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

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public boolean isNotnull() {
        return isnotnull;
    }

    public void setNotnull(boolean isnotnull) {
        this.isnotnull = isnotnull;
    }

    public boolean isUnique() {
        return isunique;
    }

    public void setUnique(boolean isunique) {
        this.isunique = isunique;
    }
}
