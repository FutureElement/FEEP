package com.feit.feep.dbms.entity.module;

import java.io.Serializable;
import java.util.List;

/**
 * ����ģ��
 */
public class FeepModule implements Serializable {

    private static final long serialVersionUID = -2434438934599214837L;

    private String id;
    /*ģ������*/
    private String name;
    /*��ʾ����*/
    private String showname;
    /*����*/
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
