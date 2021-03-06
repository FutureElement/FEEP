package com.feit.feep.dbms.service;

import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;
import com.feit.feep.dbms.entity.query.FeepQueryBean;

import java.util.List;

/**
 * 数据表操作service
 * Created by ZhangGang on 2015/6/15 0015.
 */
public interface ITableManagementService {

    /**
     * 新增数据表
     *
     * @param feepTable
     * @return
     */
    String createFeepTable(FeepTable feepTable, List<FeepTableField> feepTableFields) throws Exception;


    /**
     * 修改数据表
     *
     * @param feepTable
     * @return
     * @throws Exception
     */
    boolean modifyFeepTable(FeepTable feepTable, List<FeepTableField> feepTableFields) throws Exception;

    /**
     * 查询数据表
     *
     * @param bean
     * @return
     */
    EntityBeanSet findFeepTableList(FeepQueryBean bean) throws Exception;

    /**
     * 根据Id查询数据表信息
     *
     * @param id
     * @return
     */
    FeepTable findFeepTableById(String id) throws Exception;

    /**
     * 删除表
     *
     * @param id
     * @return
     */
    boolean deleteFeepTable(String id) throws Exception;

    /**
     * 查询指定表id的字段
     *
     * @param tableId
     * @return
     * @throws Exception
     */
    EntityBeanSet findFeepTableFieldsByTableId(String tableId) throws Exception;

}
