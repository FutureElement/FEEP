package com.feit.feep.mvc.entity;

import java.io.Serializable;
import java.util.List;

/**
 * menu entity
 * Created by ZhangGang on 2015/9/4 0004.
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = -7614335038277193377L;
    private String name;
    private String display;
    private String url;
    private List<Menu> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
