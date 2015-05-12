package com.feit.feep.dbms.crud;

import com.feit.feep.dbms.crud.middle.RetrieveRepository;
import com.feit.feep.dbms.entity.EntityBean;
import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.exception.dbms.QueryException;

public interface IRetrieve {

    RetrieveRepository getRetrieveDao();
    /**
     * 根据数据id查询数据
     *
     * @param id
     * @return EntityBean
     */
    EntityBean queryDataById(String id) throws QueryException;

    /**
     * 查询第一条数据
     *
     * @return EntityBean
     */
    EntityBean queryFirstData() throws QueryException;

    /**
     * 查询，根据设置的分页或者默认分页值
     *
     * @return EntityBeanSet
     */
    EntityBeanSet queryList() throws QueryException;

    /**
     * 查询，不进行分页
     *
     * @return EntityBeanSet
     */
    EntityBeanSet queryListWithoutPages() throws QueryException;

    /**
     * 根据SQL语句查询
     *
     * @param sql
     * @return EntityBeanSet
     */
    EntityBeanSet queryListBySql(String sql) throws QueryException;

    /**
     * 根据SQL语句查询
     *
     * @param sql
     * @return EntityBean
     */
    EntityBean queryFirstDataBySql(String sql) throws QueryException;

    /**
     * 统计数量
     * @return
     * @throws QueryException
     */
    int countDate() throws QueryException;

}
