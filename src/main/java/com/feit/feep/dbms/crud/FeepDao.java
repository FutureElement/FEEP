package com.feit.feep.dbms.crud;

import java.util.LinkedList;
import java.util.List;

import com.feit.feep.dbms.entity.query.Condition;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.dbms.entity.query.QueryParameter;
import com.feit.feep.dbms.entity.query.SortField;

/**
 * Feep查询公共类
 * 
 * @author ZhangGang
 *
 */
public class FeepDao implements IDefaultDao {

    protected static final int MODE_INSERT   = 0;
    protected static final int MODE_UPDATE   = 1;
    protected static final int MODE_DELETE   = 2;
    protected static final int MODE_SELECT   = 3;

    protected FeepQueryBean    feepQueryBean = new FeepQueryBean();

    @Override
    public void setModuleName(String moduleName) {
        feepQueryBean.setModuleName(moduleName);
    }

    @Override
    public void addQueryParameter(String fieldName, String value) {
        addQueryParameter(fieldName, value, null);
    }

    @Override
    public void addQueryParameter(String fieldName, String value, Condition cnd) {
        feepQueryBean.getQueryParameters().add(new QueryParameter(fieldName, value, cnd));
    }

    @Override
    public void addQueryParameters(List<QueryParameter> queryParameters) {
        feepQueryBean.getQueryParameters().addAll(queryParameters);
    }

    @Override
    public void addField(String field) {
        feepQueryBean.getFields().add(field);
    }

    @Override
    public void addFields(String[] fields) {
        if (null != fields) {
            for (String field : fields) {
                feepQueryBean.getFields().add(field);
            }
        }
    }

    @Override
    public void setPageIndex(int pageIndex) {
        feepQueryBean.setPageIndex(pageIndex);
    }

    @Override
    public void setPageSize(int pageSize) {
        feepQueryBean.setPageSize(pageSize);
    }

    @Override
    public void addSortField(String sortField, boolean isAsc) {
        feepQueryBean.getSortFields().add(new SortField(sortField, isAsc));
    }

    @Override
    public void addSortFields(List<SortField> sortFields) {
        feepQueryBean.getSortFields().addAll(sortFields);
    }

    @Override
    public void cleanAll() {
        feepQueryBean = new FeepQueryBean();
    }

    @Override
    public void cleanQueryParameters() {
        feepQueryBean.setQueryParameters(new LinkedList<QueryParameter>());
    }

    @Override
    public void cleanFields() {
        feepQueryBean.setFields(new LinkedList<String>());
    }

    @Override
    public void cleanSortField() {
        feepQueryBean.setSortFields(new LinkedList<SortField>());
    }

    public FeepQueryBean getFeepQueryBean() {
        return feepQueryBean;
    }

    public void setFeepQueryBean(FeepQueryBean feepQueryBean) {
        this.feepQueryBean = feepQueryBean;
    }

}
