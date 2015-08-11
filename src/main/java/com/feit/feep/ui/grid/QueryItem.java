package com.feit.feep.ui.grid;


import com.feit.feep.ui.dropdown.SelectItem;

/**
 * 查询字段
 * Created by ZhangGang on 2015/8/1 0001.
 */
public class QueryItem extends SelectItem {

    private static final long serialVersionUID = 7469406646111382750L;

    private String fieldType;

    private String code;

    public QueryItem() {
        super();
    }

    public QueryItem(String codeId, String codeValue) {
        super(codeId, codeValue);
    }

    public QueryItem(String codeId, String codeValue, String fieldType, String code) {
        this(codeId, codeValue);
        this.fieldType = fieldType;
        this.code = code;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
