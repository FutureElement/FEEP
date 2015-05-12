package com.feit.feep.dbms.crud;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.crud.impl.CreateImpl;
import com.feit.feep.dbms.crud.impl.DeleteImpl;
import com.feit.feep.dbms.crud.impl.RetrieveImpl;
import com.feit.feep.dbms.crud.impl.UpdateImpl;
import com.feit.feep.dbms.crud.middle.RetrieveApp;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

import com.feit.feep.dbms.build.GeneratorSqlBuild;
import com.feit.feep.dbms.entity.EntityBean;
import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.query.Page;
import com.feit.feep.exception.dbms.QueryException;

public class FeepDao extends DefaultDao implements RetrieveApp {

    @Override
    public EntityBean queryDataById(String id) throws QueryException {
        return getRetrieveDao().queryDataById(id);
    }

    @Override
    public EntityBean queryFirstData() throws QueryException {
        return getRetrieveDao().queryFirstData();
    }

    @Override
    public EntityBeanSet queryList() throws QueryException {
        return getRetrieveDao().queryList();
    }

    @Override
    public EntityBeanSet queryListWithoutPages() throws QueryException {
        return getRetrieveDao().queryListWithoutPages();
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
        return getRetrieveDao().countDate();
    }

    public ICreate getCreateDao() {
        return Global.getInstance().getApplicationContext().getBean(CreateImpl.class);
    }

    public IDelete getDeleteDao() {
        return Global.getInstance().getApplicationContext().getBean(DeleteImpl.class);
    }

    public IUpdate geUpdateDao() {
        return Global.getInstance().getApplicationContext().getBean(UpdateImpl.class);
    }

    @Override
    public IRetrieve getRetrieveDao() {
        return Global.getInstance().getApplicationContext().getBean(RetrieveImpl.class);
    }
}
