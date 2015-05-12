package com.feit.feep.dbms.crud.middle;

import com.feit.feep.dbms.crud.IDelete;
import com.feit.feep.dbms.entity.query.FeepQueryBean;

/**
 * Created by zhanggang on 2015/5/12.
 */
public interface DeleteRepository extends IDelete {
    void setQueryBean(FeepQueryBean feepQueryBean);
}
