package com.feit.feep.dbms.crud;

import java.util.List;

import com.feit.feep.dbms.entity.query.Condition;
import com.feit.feep.dbms.entity.query.QueryParameter;
import com.feit.feep.dbms.entity.query.SortField;

public interface IDefaultDao {

    /**
     * 添加模型名
     * 
     * @param moduleName
     */
    void setModuleName(String moduleName);

    /**
     * 添加查询条件
     * 
     * @param fieldName
     * @param value
     */
    void addQueryParameter(String fieldName, String value);

    /**
     * 添加查询条件
     * 
     * @param fieldName
     * @param value
     * @param cnd
     */
    void addQueryParameter(String fieldName, String value, Condition cnd);

    /**
     * 批量添加查询条件
     * 
     * @param queryParameters
     */
    void addQueryParameters(List<QueryParameter> queryParameters);

    /**
     * 增加查询字段
     * 
     * @param field
     */
    void addField(String field);

    /**
     * 批量增加查询字段
     * 
     * @param fields
     */
    void addFields(String[] fields);

    /**
     * 设置当前页码
     * 
     * @param pageIndex
     */
    void setPageIndex(int pageIndex);

    /**
     * 设置页数大小
     * 
     * @param pageSize
     */
    void setPageSize(int pageSize);

    /**
     * 添加排序字段
     * 
     * @param sortField
     * @param isAsc
     */
    void addSortField(String sortField, boolean isAsc);

    /**
     * 批量添加排序字段
     * 
     * @param sortFields
     */
    void addSortFields(List<SortField> sortFields);

    /**
     * 清空所有数据
     */
    void cleanAll();

    /**
     * 清空查询条件
     */
    void cleanQueryParameters();

    /**
     * 清空查询字段
     */
    void cleanFields();

    /**
     * 清空排序字段
     */
    void cleanSortField();
}
