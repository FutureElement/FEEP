package com.feit.feep.dbms.crud.middle;

import com.feit.feep.dbms.crud.IUpdate;

/**
 * Created by zhanggang on 2015/5/12.
 */
public interface UpdateApp extends IUpdate {
    IUpdate getUpdateDao();
}
