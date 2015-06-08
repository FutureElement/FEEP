package com.feit.feep.dbms.table;

import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.exception.dbms.TableException;

import java.util.List;

/**
 * 数据表dao接口
 * Created by zhanggang on 2015/5/15.
 */
public interface IBasicTableDao {

    String addTable(FeepTable feepTable) throws TableException;

    FeepTable getTableById(String id) throws TableException;

    boolean modifyTable(FeepTable table) throws TableException;

    boolean deleteTableById(String id) throws TableException;

    List<FeepTable> queryFeepTable() throws TableException;

}
