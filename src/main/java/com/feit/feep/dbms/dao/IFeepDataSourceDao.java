package com.feit.feep.dbms.dao;

import com.feit.feep.dbms.entity.module.FeepDataSource;
import com.feit.feep.exception.dbms.TableException;

import java.util.List;

/**
 * 数据源dao接口
 * Created by zhanggang on 2015/7/1.
 */
public interface IFeepDataSourceDao {

    /**
     * 新增一个数据源
     *
     * @param dataSource
     * @return
     * @throws TableException
     */
    public String addDataSource(FeepDataSource dataSource) throws TableException;

    /**
     * 根据Id删除数据源
     *
     * @param id
     * @return
     * @throws TableException
     */
    public boolean deleteDataSource(String id) throws TableException;

    /**
     * 修改数据源信息
     *
     * @param dataSource
     * @return
     * @throws TableException
     */
    public boolean updateDataSource(FeepDataSource dataSource) throws TableException;

    /**
     * 查询所有数据源
     *
     * @return
     * @throws TableException
     */
    public List<FeepDataSource> findAll() throws TableException;

    /**
     * 根据Id查询数据源
     *
     * @param id
     * @return
     * @throws TableException
     */
    public FeepDataSource findDataSourceById(String id) throws TableException;

    /**
     * 根据数据源名称查询数据源信息
     *
     * @param name
     * @return
     * @throws TableException
     */
    public FeepDataSource findDataSourceByName(String name) throws TableException;

}
