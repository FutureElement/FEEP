package com.feit.feep.dbms.crud.middle;

import com.feit.feep.dbms.crud.IRetrieve;
import com.feit.feep.dbms.entity.EntityBean;
import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.exception.dbms.QueryException;

/**
 * Created by zhanggang on 2015/5/12.
 */
public interface RetrieveRepository {
    /**
     * 根据数据id查询数据
     *
     * @param id
     * @return EntityBean
     */
    EntityBean queryDataById(String id, FeepQueryBean feepQueryBean) throws QueryException;

    /**
     * 查询第一条数据
     *
     * @return EntityBean
     */
    EntityBean queryFirstData(FeepQueryBean feepQueryBean) throws QueryException;

    /**
     * 查询，根据设置的分页或者默认分页值
     *
     * @return EntityBeanSet
     */
    EntityBeanSet queryList(FeepQueryBean feepQueryBean) throws QueryException;

    /**
     * 查询，不进行分页
     *
     * @return EntityBeanSet
     */
    EntityBeanSet queryListWithoutPages(FeepQueryBean feepQueryBean) throws QueryException;

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
     *
     * @return
     * @throws QueryException
     */
    int countDate(FeepQueryBean feepQueryBean) throws QueryException;
}
