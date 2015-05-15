package com.feit.feep.dbms.table;

import com.feit.feep.dbms.entity.module.DataTable;

/**
 * Created by zhanggang on 2015/5/15.
 */
public interface IBasicTableDao {
    DataTable getTableById(String id);
}
