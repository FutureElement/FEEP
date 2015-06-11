package com.feit.feep.dbms.table;

import java.util.List;

import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.exception.dbms.TableException;

/**
 * 数据表dao接口
 * Created by zhanggang on 2015/5/15.
 */
public interface IBasicTableDao {

    /**
     * 创建表
     *
     * @param feepTable 数据表信息
     * @return
     * @throws TableException
     */
    Boolean createTable(FeepTable feepTable);

    /**
     * 根据id获取数据表信息
     *
     * @param id
     * @return
     * @throws TableException
     */
    FeepTable getTableById(String id) throws TableException;

    /**
     * 修改数据表
     *
     * @param table
     * @return
     * @throws TableException
     */
    boolean modifyTable(FeepTable table) throws TableException;

    /**
     * 删除数据表
     *
     * @param id
     * @return
     * @throws TableException
     */
    boolean deleteTableById(String id) throws TableException;


    /**
     * 查询数据表
     *
     * @param feepQueryBean
     * @return
     * @throws TableException
     */
    List<FeepTable> queryFeepTable(FeepQueryBean feepQueryBean) throws TableException;

    /**
     * 获取FeepTable总数
     *
     * @return
     * @throws TableException
     */
    int countFeepTable() throws TableException;

}
