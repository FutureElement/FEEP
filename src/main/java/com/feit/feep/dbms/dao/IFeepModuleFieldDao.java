package com.feit.feep.dbms.dao;

import com.feit.feep.dbms.entity.module.FeepModuleField;
import com.feit.feep.exception.dbms.TableException;

import java.util.List;

/**
 * 数据模型字段dao接口
 * Created by zhanggang on 2015/7/3.
 */
public interface IFeepModuleFieldDao {
    /**
     * 新增数据模型字段
     *
     * @param moduleField
     * @return
     * @throws TableException
     */
    public String addModuleField(FeepModuleField moduleField) throws TableException;

    /**
     * 批量新增数据模型字段
     *
     * @param moduleFields
     * @return
     * @throws TableException
     */
    public String[] addModuleFields(List<FeepModuleField> moduleFields) throws TableException;

    /**
     * 根据id删除数据模型字段
     *
     * @param id
     * @return
     * @throws TableException
     */
    public boolean deleteModuleFieldById(String id) throws TableException;

    /**
     * 批量删除数据模型字段
     *
     * @param ids
     * @return
     * @throws TableException
     */
    public boolean deleteModuleFieldByIds(String[] ids) throws TableException;

    /**
     * 根据数据模型id删除数据模型字段
     *
     * @param moduleId
     * @return
     * @throws TableException
     */
    public boolean deleteModuleFieldByModuleId(String moduleId) throws TableException;

    /**
     * 修改数据模型信息
     *
     * @param moduleField
     * @return
     * @throws TableException
     */
    public boolean updateModuleField(FeepModuleField moduleField) throws TableException;

    /**
     * 批量修改数据模型信息
     *
     * @param moduleFields
     * @return
     * @throws TableException
     */
    public boolean batchUpdateModuleFields(List<FeepModuleField> moduleFields) throws TableException;

    /**
     * 根据数据模型id查询数据模型字段信息
     *
     * @param moduleId
     * @return
     * @throws TableException
     */
    public List<FeepModuleField> findModuleFieldsByModuleId(String moduleId) throws TableException;

    /**
     * 根据id查询数据模型字段信息
     *
     * @param id
     * @return
     * @throws TableException
     */
    public FeepModuleField findModuleFieldById(String id) throws TableException;

    /**
     * 查询所有数据模型字段信息
     *
     * @return
     * @throws TableException
     */
    public List<FeepModuleField> findAll() throws TableException;
}
