package com.feit.feep.core.resource.entity;

import java.io.Serializable;

/**
 * feep资源
 * Created by ZhangGang on 2015/9/6 0006.
 */
public class FeepResource implements Serializable {
    private static final long serialVersionUID = 5809984465058053681L;
    private String id;
    private String parentId;
    private String name;
    private String url;
    private String display;
    private String system;
    private int sort;

    public static final String[] column = {"id", "parentId", "name", "url", "display", "system", "sort"};
    public static final String COL_NAME = "name";
    public static final String COL_PARENTID = "parentId";
    public static final String COL_SORT = "sort";

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
