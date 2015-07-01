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

    public String addModule(FeepModule feepModule) throws TableException;

    public boolean deleteModuleById(String id) throws TableException;

    public boolean updateModule(FeepModule feepModule) throws TableException;

    public FeepModule findModuleById(String id) throws TableException;

    public List<FeepModule> queryModule(FeepQueryBean queryBean) throws TableException;

    public List<FeepModule> findAll() throws TableException;
}
