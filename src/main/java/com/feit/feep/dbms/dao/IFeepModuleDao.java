package com.feit.feep.dbms.dao;

import com.feit.feep.dbms.entity.module.FeepModule;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.exception.dbms.TableException;

import java.util.List;

/**
 * 数据模型Dao接口
 * Created by zhanggang on 2015/7/1.
 */
public interface IFeepModuleDao {

    /**
     * 新增一个数据模型
     *
     * @param feepModule
     * @return
     * @throws TableException
     */
    public String addModule(FeepModule feepModule) throws TableException;

    /**
     * 根据id删除数据模型
     *
     * @param id
     * @return
     * @throws TableException
     */
    public boolean deleteModuleById(String id) throws TableException;

    /**
     * 修改数据模型
     *
     * @param feepModule
     * @return
     * @throws TableException
     */
    public boolean updateModule(FeepModule feepModule) throws TableException;

    /**
     * 根据id查询模型信息
     *
     * @param id
     * @return
     * @throws TableException
     */
    public FeepModule findModuleById(String id) throws TableException;

    /**
     * 查询模型
     *
     * @param queryBean
     * @return
     * @throws TableException
     */
    public List<FeepModule> queryModule(FeepQueryBean queryBean) throws TableException;

    /**
     * 查询所有模型
     *
     * @return
     * @throws TableException
     */
    public List<FeepModule> findAll() throws TableException;
}
