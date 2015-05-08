package com.feit.feep.dbms.entity.query;

import java.util.LinkedList;
import java.util.List;

import com.feit.feep.core.Global;

public class FeepQueryBean {

    private String               moduleName;
    private List<QueryParameter> queryParameters;
    private List<String>         fields;
    private int                  pageSize;
    private int                  pageIndex;
    private List<SortField>      sortFields;

    public FeepQueryBean() {
        moduleName = null;
        queryParameters = new LinkedList<QueryParameter>();
        fields = new LinkedList<String>();
        sortFields = new LinkedList<SortField>();
        pageSize = Global.getInstance().getFeepConfig().getDefaultPageSize();
        pageIndex = 1;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<QueryParameter> getQueryParameters() {
        return queryParameters;
    }

    public void setQueryParameters(List<QueryParameter> queryParameters) {
        this.queryParameters = queryParameters;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<SortField> getSortFields() {
        return sortFields;
    }

    public void setSortFields(List<SortField> sortFields) {
        this.sortFields = sortFields;
    }

}
