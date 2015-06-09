package com.feit.feep.ui.grid;

import java.io.Serializable;

/**
 * Created by ZhangGang on 2015/6/9 0009.
 */
public class Column implements Serializable {
    private static final long serialVersionUID = 3535573922584313432L;
    private int width;
    private String textAlign;
    private String name;
    private String display;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(String textAlign) {
        this.textAlign = textAlign;
    }

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
}
