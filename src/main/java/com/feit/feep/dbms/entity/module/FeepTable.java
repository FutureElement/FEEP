package com.feit.feep.dbms.entity.module;

import java.io.Serializable;
import java.util.List;

/**
 * 数据表
 */
public class FeepTable implements Serializable {

    private static final long serialVersionUID = 6762062178277594096L;

    private String id;
    /*物理表名*/
    private String name;
    /*中文名*/
    private String showname;
    /*类型*/
    private String tabletype;
    /*描述*/
    private String description;
    /*数据源id*/
    private String datasourceid;
    /*数据表字段*/
    private List<FeepTableField> tableFields;

    public List<FeepTableField> getTableFields() {
        return tableFields;
    }

    public void setTableFields(List<FeepTableField> tableFields) {
        this.tableFields = tableFields;
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

    public String getTabletype() {
        return tabletype;
    }

    public void setTabletype(String tabletype) {
        this.tabletype = tabletype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatasourceid() {
        return datasourceid;
    }

    public void setDatasourceid(String datasourceid) {
        this.datasourceid = datasourceid;
    }

}
