package com.feit.feep.dbms.entity.module;

import java.io.Serializable;

/**
 * 数据模型
 */
public class FeepModule implements Serializable {

    private static final long serialVersionUID = -2434438934599214837L;

    public static final String[] column = {"id", "name", "showname", "description"};
    private String id;
    /*模型名称*/
    private String name;
    /*显示名称*/
    private String showname;
    /*描述*/
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
