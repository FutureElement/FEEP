package com.feit.feep.dbms.crud.middle;

import com.feit.feep.dbms.crud.IRetrieve;
import com.feit.feep.dbms.entity.query.FeepQueryBean;

/**
 * Created by zhanggang on 2015/5/12.
 */
public interface RetrieveRepository extends IRetrieve {
    void setQueryBean(FeepQueryBean feepQueryBean);
}
