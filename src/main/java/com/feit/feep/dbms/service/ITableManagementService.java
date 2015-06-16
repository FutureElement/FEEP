package com.feit.feep.dbms.service;

import com.feit.feep.dbms.entity.module.FeepTable;
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
    String createFeepTable(FeepTable feepTable);

    /**
     * 查询数据表
     *
     * @param bean
     * @return
     */
    List<FeepTable> findFeepTableList(FeepQueryBean bean);

    /**
     * 根据Id查询数据表信息
     *
     * @param id
     * @return
     */
    FeepTable findFeepTableById(String id);

    /**
     * 修改表
     *
     * @param feepTable
     * @return
     */
    boolean modifyFeepTable(FeepTable feepTable);

    /**
     * 删除表
     *
     * @param id
     * @return
     */
    boolean deleteFeepTable(String id);
}
