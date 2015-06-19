package com.feit.feep.dbms.dao;

import com.feit.feep.dbms.entity.EntityBean;
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
     * 根据表id获取字段信息
     *
     * @param tableId
     * @return
     * @throws TableException
     */
    public List<EntityBean> getFeepTableFieldByTableId(String tableId) throws TableException;

    /**
     * 获取字段信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    public FeepTableField findFeepTableFieldById(String id) throws Exception;

    /**
     * 删除数据表字段
     *
     * @param feepTableField
     * @return
     * @throws Exception
     */
    public boolean removeTableColumn(FeepTableField feepTableField) throws Exception;

    /**
     * 增加数据表字段
     *
     * @param feepTableField
     * @return
     * @throws Exception
     */
    public boolean addTableColumn(FeepTableField feepTableField) throws Exception;

    /**
     * 清除字段数据
     *
     * @param feepTableField
     * @return
     * @throws Exception
     */
    public boolean cleanFieldData(FeepTableField feepTableField) throws Exception;

}
