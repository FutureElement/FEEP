package com.feit.feep.dbms.service;

import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.module.FeepModule;
import com.feit.feep.dbms.entity.module.FeepModuleField;
import com.feit.feep.dbms.entity.module.FeepSubTable;
import com.feit.feep.dbms.entity.query.FeepQueryBean;

import java.util.List;

/**
 * 数据模型service接口
 * Created by zhanggang on 2015/7/3.
 */
public interface IModuleManagementService {

    /**
     * 增加一个数据模型
     *
     * @param feepModule
     * @param moduleFields
     * @param subTableList
     * @return
     * @throws Exception
     */
    public String addModule(FeepModule feepModule, List<FeepModuleField> moduleFields, List<FeepSubTable> subTableList) throws Exception;

    /**
     * 根据id删除模型
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteModuleById(String id) throws Exception;

    /**
     * 修改数据模型信息
     *
     * @param feepModule
     * @param moduleFields
     * @param subTableList
     * @return
     * @throws Exception
     */
    public boolean modifyModule(FeepModule feepModule, List<FeepModuleField> moduleFields, List<FeepSubTable> subTableList) throws Exception;

    /**
     * 根据id查询数据模型信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    public FeepModule findFeepModuleById(String id) throws Exception;

    /**
     * 查询数据模型
     *
     * @param queryBean
     * @return
     * @throws Exception
     */
    public EntityBeanSet queryFeepModule(FeepQueryBean queryBean) throws Exception;

    /**
     * 根据模型id查询模型字段
     *
     * @param moduleId
     * @return
     * @throws Exception
     */
    public EntityBeanSet getModuleFieldsByModuleId(String moduleId) throws Exception;

    /**
     * 根据模型id查询从表信息
     *
     * @param moduleId
     * @return
     * @throws Exception
     */
    public List<FeepSubTable> getFeepSubTableByModuleId(String moduleId) throws Exception;
}
