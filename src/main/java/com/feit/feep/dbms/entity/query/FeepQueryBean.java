package com.feit.feep.dbms.entity.query;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.TypeReference;
import com.feit.feep.core.Global;
import com.feit.feep.util.FeepUtil;
import com.feit.feep.util.json.FeepJsonUtil;

public class FeepQueryBean {

    private String moduleName;
    private List<QueryParameter> queryParameters;
    private List<String> fields;
    private int pageSize;
    private int pageIndex;
    private List<SortField> sortFields;
    private Map<String, String> customParameters;

    public FeepQueryBean() {
        moduleName = null;
        queryParameters = new LinkedList<QueryParameter>();
        fields = new LinkedList<String>();
        sortFields = new LinkedList<SortField>();
        pageSize = Global.getInstance().getFeepConfig().getDefaultPageSize();
        pageIndex = 1;
        customParameters = new HashMap<String, String>();

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

    public void setParams(String params) {
        if (!FeepUtil.isNull(params)) {
            try {
                customParameters = FeepJsonUtil.parseJson(params, new TypeReference<Map<String, String>>() {
                });
            } catch (Exception e) {
                Global.getInstance().logError(e);
            }
        }
    }

    public String getCustomParameter(String key) {
        return customParameters.get(key);
    }

}
