package com.feit.feep.dbms.dao;

import com.feit.feep.dbms.entity.module.FeepTableField;
import com.feit.feep.exception.dbms.TableException;

import java.util.List;

/**
 * Created by ZhangGang on 2015/6/17 0017.
 */
public interface IFeepTableFieldDao {

    /**
     * 新增字段信息
     *
     * @param feepTableField
     * @return
     * @throws TableException
     */
    public String insertTableField(FeepTableField feepTableField) throws TableException;

    /**
     * 批量新增字段信息
     *
     * @param tableFieldList
     * @return
     * @throws TableException
     */
    public String[] insertTableFields(List<FeepTableField> tableFieldList) throws TableException;

    /**
     * 删除字段信息
     *
     * @param id
     * @return
     * @throws TableException
     */
    public boolean deleteTableFieldById(String id) throws TableException;

    /**
     * 批量删除字段信息
     *
     * @param ids
     * @return
     * @throws TableException
     */
    public boolean deleteTableFieldsByIds(String[] ids) throws TableException;

    /**
     * 根据tableid删除字段信息
     *
     * @param tableid
     * @return
     * @throws TableException
     */
    public boolean deleteTableFieldsByTableId(String tableid) throws TableException;

    /**
     * 根据表id获取字段信息
     *
     * @param tableId
     * @return
     * @throws TableException
     */
    public List<FeepTableField> getFeepTableFieldByTableId(String tableId) throws TableException;

    /**
     * 获取字段信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    public FeepTableField findFeepTableFieldById(String id) throws TableException;


    /**
     * 查询所有字段
     *
     * @return
     * @throws TableException
     */
    public List<FeepTableField> findAllFields() throws TableException;

    /**
     * 删除数据表字段
     *
     * @param tableName
     * @param fieldName
     * @return
     * @throws TableException
     */
    public boolean removeTableColumn(String tableName, String fieldName) throws TableException;

    /**
     * 增加数据表字段
     *
     * @param tableName
     * @param feepTableField
     * @return
     * @throws Exception
     */
    public boolean addTableColumn(String tableName, FeepTableField feepTableField) throws TableException;

    /**
     * 增加字段非空约束
     *
     * @param tableName
     * @param fieldName
     * @return
     * @throws TableException
     */
    public boolean addNotNullConstraint(String tableName, String fieldName) throws TableException;

    /**
     * 增加字段唯一约束
     *
     * @param tableName
     * @param fieldName
     * @return
     * @throws TableException
     */
    public boolean addUniqueConstraint(String tableName, String fieldName) throws TableException;

    /**
     * 移除字段非空约束
     *
     * @param tableName
     * @param fieldName
     * @return
     * @throws TableException
     */
    public boolean removeNotNullConstraint(String tableName, String fieldName) throws TableException;

    /**
     * 移除字段唯一约束
     *
     * @param tableName
     * @param fieldName
     * @return
     * @throws TableException
     */
    public boolean removeUniqueConstraint(String tableName, String fieldName) throws TableException;

    /**
     * 修改字段名称
     *
     * @param tableName
     * @param fieldName
     * @param newName
     * @return
     * @throws TableException
     */
    public boolean modifyTableColumnName(String tableName, String fieldName, String newName) throws TableException;


    /**
     * 修改字段范围
     *
     * @param tableName
     * @param feepTableField
     * @return
     * @throws TableException
     */
    public boolean modifyTableColumnRange(String tableName, FeepTableField feepTableField) throws TableException;

    /**
     * 修改字段信息
     *
     * @param feepTableField
     * @return
     * @throws TableException
     */
    public boolean updateTableFieldInfo(FeepTableField feepTableField) throws TableException;
}
