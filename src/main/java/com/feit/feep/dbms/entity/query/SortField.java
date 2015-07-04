package com.feit.feep.dbms.entity.query;

public class SortField {
    private String fieldName;
    private boolean isAsc;

    public SortField() {
    }

    public SortField(String fieldName) {
        this.fieldName = fieldName;
        this.isAsc = true;
    }

    public SortField(String fieldName, boolean isAsc) {
        this.fieldName = fieldName;
        this.isAsc = isAsc;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public boolean isAsc() {
        return isAsc;
    }

    public void setAsc(boolean isAsc) {
        this.isAsc = isAsc;
    }

}
