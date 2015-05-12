package com.feit.feep.dbms.crud;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.crud.impl.CreateImpl;
import com.feit.feep.dbms.crud.impl.DeleteImpl;
import com.feit.feep.dbms.crud.impl.RetrieveImpl;
import com.feit.feep.dbms.crud.impl.UpdateImpl;
import com.feit.feep.dbms.crud.middle.CreateRepository;
import com.feit.feep.dbms.crud.middle.DeleteRepository;

import com.feit.feep.dbms.crud.middle.RetrieveRepository;
import com.feit.feep.dbms.crud.middle.UpdateRepository;
import com.feit.feep.dbms.entity.EntityBean;
import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.exception.dbms.QueryException;

public class FeepDao extends DefaultDao implements IRetrieve {

    @Override
    public EntityBean queryDataById(String id) throws QueryException {
        return getRetrieveDao().queryDataById(id, feepQueryBean);
    }

    @Override
    public EntityBean queryFirstData() throws QueryException {
        return getRetrieveDao().queryFirstData(feepQueryBean);
    }

    @Override
    public EntityBeanSet queryList() throws QueryException {
        return getRetrieveDao().queryList(feepQueryBean);
    }

    @Override
    public EntityBeanSet queryListWithoutPages() throws QueryException {
        return getRetrieveDao().queryListWithoutPages(feepQueryBean);
    }

    @Override
    public EntityBeanSet queryListBySql(String sql) throws QueryException {
        return getRetrieveDao().queryListBySql(sql);
    }

    @Override
    public EntityBean queryFirstDataBySql(String sql) throws QueryException {
        return getRetrieveDao().queryFirstDataBySql(sql);
    }

    @Override
    public int countDate() throws QueryException {
        return getRetrieveDao().countDate(feepQueryBean);
    }

    public CreateRepository getCreateDao() {
        return Global.getInstance().getApplicationContext().getBean(CreateImpl.class);
    }

    public DeleteRepository getDeleteDao() {
        return Global.getInstance().getApplicationContext().getBean(DeleteImpl.class);
    }

    public UpdateRepository geUpdateDao() {
        return Global.getInstance().getApplicationContext().getBean(UpdateImpl.class);
    }

    @Override
    public RetrieveRepository getRetrieveDao() {
        return Global.getInstance().getApplicationContext().getBean(RetrieveImpl.class);
    }
}
