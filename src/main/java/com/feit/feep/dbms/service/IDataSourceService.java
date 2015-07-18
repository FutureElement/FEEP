package com.feit.feep.dbms.service;

import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.module.FeepDataSource;


/**
 * 数据源service接口
 * Created by zhanggang on 2015/7/2.
 */
public interface IDataSourceService {
    /**
     * 新增数据源
     *
     * @param feepDataSource
     * @return
     * @throws Exception
     */
    public String addDataSource(FeepDataSource feepDataSource) throws Exception;

    /**
     * 删除数据源
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteDataSource(String id) throws Exception;

    /**
     * 修改数据源
     *
     * @param feepDataSource
     * @return
     * @throws Exception
     */
    public boolean modifyDataSource(FeepDataSource feepDataSource) throws Exception;

    /**
     * 查询所有数据源
     *
     * @return
     * @throws Exception
     */
    public EntityBeanSet findAllDataSource() throws Exception;

    /**
     * 根据id查询数据源
     *
     * @param id
     * @return
     * @throws Exception
     */
    public FeepDataSource findDataSourceById(String id) throws Exception;

    /**
     * 根据名称查询数据源
     *
     * @param name
     * @return
     * @throws Exception
     */
    public FeepDataSource findDataSourceByName(String name) throws Exception;

    /**
     * 查询默认数据源信息
     *
     * @return
     * @throws Exception
     */
    public FeepDataSource getDefaultDataSourceInfo() throws Exception;

}
