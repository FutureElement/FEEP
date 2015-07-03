package com.feit.feep.dbms.dao;

import com.feit.feep.dbms.entity.module.FeepTableFieldRelation;
import com.feit.feep.exception.dbms.TableException;

import java.util.List;

/**
 * 字段关系dao接口
 * Created by zhanggang on 2015/7/3.
 */
public interface IFeepTableFieldRelationDao {

    /**
     * 新增字段关系
     *
     * @param tableFieldRelation
     * @return
     * @throws TableException
     */
    public String addTableFieldRelation(FeepTableFieldRelation tableFieldRelation) throws TableException;

    /**
     * 批量新增字段关系
     *
     * @param tableFieldRelationList
     * @return
     * @throws TableException
     */
    public String[] addTableFieldRelations(List<FeepTableFieldRelation> tableFieldRelationList) throws TableException;

    /**
     * 根据id删除字段关系
     *
     * @param id
     * @return
     * @throws TableException
     */
    public boolean deleteTableFieldRelationById(String id) throws TableException;

    /**
     * 批量删除字段关系
     *
     * @param ids
     * @return
     * @throws TableException
     */
    public boolean deleteTableFieldRelationByIds(String[] ids) throws TableException;

    /**
     * 根据字段id删除字段关系
     *
     * @param tableFieldRelationid
     * @return
     * @throws TableException
     */
    public boolean deleteTableFieldRelationByRelationId(String tableFieldRelationid) throws TableException;

    /**
     * 修改字段关系
     *
     * @param tableFieldRelation
     * @return
     * @throws TableException
     */
    public boolean updateTableFieldRelation(FeepTableFieldRelation tableFieldRelation) throws TableException;

    /**
     * 批量修改字段关系
     *
     * @param tableFieldRelationList
     * @return
     * @throws TableException
     */
    public boolean batchUpdateTableFieldRelation(List<FeepTableFieldRelation> tableFieldRelationList) throws TableException;

    /**
     * 查询所有字段关系
     *
     * @return
     * @throws TableException
     */
    public List<FeepTableFieldRelation> findAll() throws TableException;
}
