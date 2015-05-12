package com.feit.feep.dbms.crud.middle;

import com.feit.feep.dbms.crud.IRetrieve;

/**
 * Created by zhanggang on 2015/5/12.
 */
public interface RetrieveApp extends IRetrieve {
    IRetrieve getRetrieveDao();
}
