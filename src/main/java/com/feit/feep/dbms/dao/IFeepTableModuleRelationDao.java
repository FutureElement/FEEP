package com.feit.feep.dbms.dao;

import com.feit.feep.dbms.entity.module.FeepTableModuleRelation;
import com.feit.feep.exception.dbms.TableException;

import java.util.List;

/**
 * 数据表模型关系dao接口
 * Created by zhanggang on 2015/7/3.
 */
public interface IFeepTableModuleRelationDao {

    /**
     * 新增模型关系
     *
     * @param tableModuleRelation
     * @return
     * @throws TableException
     */
    public String addTableModuleRelation(FeepTableModuleRelation tableModuleRelation) throws TableException;

    /**
     * 批量新增模型关系
     *
     * @param tableModuleRelationList
     * @return
     * @throws TableException
     */
    public String[] addTableModuleRelations(List<FeepTableModuleRelation> tableModuleRelationList) throws TableException;

    /**
     * 根据id删除模型关系
     *
     * @param id
     * @return
     * @throws TableException
     */
    public boolean deleteTableModuleRelationById(String id) throws TableException;

    /**
     * 批量删除模型关系
     *
     * @param ids
     * @return
     * @throws TableException
     */
    public boolean deleteTableModuleRelationByIds(String[] ids) throws TableException;

    /**
     * 根据模型id删除模型关系
     *
     * @param moduleId
     * @return
     * @throws TableException
     */
    public boolean deleteTableModuleRelationByModuleId(String moduleId) throws TableException;

    /**
     * 修改模型关系
     *
     * @param tableModuleRelation
     * @return
     * @throws TableException
     */
    public boolean updateTableModuleRelation(FeepTableModuleRelation tableModuleRelation) throws TableException;

    /**
     * 批量修改模型关系
     *
     * @param tableModuleRelationList
     * @return
     * @throws TableException
     */
    public boolean batchUpdateTableModuleRelation(List<FeepTableModuleRelation> tableModuleRelationList) throws TableException;

    /**
     * 查询所有模型关系
     *
     * @return
     * @throws TableException
     */
    public List<FeepTableModuleRelation> findAll() throws TableException;

}

