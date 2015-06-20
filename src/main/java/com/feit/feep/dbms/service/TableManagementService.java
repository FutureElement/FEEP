package com.feit.feep.dbms.service;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.dao.IFeepTableDao;
import com.feit.feep.dbms.dao.IFeepTableFieldDao;
import com.feit.feep.dbms.entity.EntityBean;
import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.dbms.entity.query.Page;
import com.feit.feep.exception.dbms.TableException;
import com.feit.feep.util.FeepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.LinkedList;
import java.util.List;

/**
 * 数据表管理service
 * Created by ZhangGang on 2015/6/15 0015.
 */
@Service
public class TableManagementService implements ITableManagementService {

    @Autowired
    private IFeepTableDao feepTableDao;

    @Autowired
    private IFeepTableFieldDao feepTableFieldDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public String createFeepTable(final FeepTable feepTable, final List<FeepTableField> tableFields) throws Exception {
        return transactionTemplate.execute(new TransactionCallback<String>() {
            @Override
            public String doInTransaction(TransactionStatus transactionStatus) {
                String newId;
                try {
                    //1.create table
                    feepTableDao.createTable(feepTable, tableFields);
                    //2.insert to feeptable
                    newId = feepTableDao.insertFeepTable(feepTable);
                    //3.insert to feeptableField
                    if (!FeepUtil.isNull(tableFields)) {
                        feepTableFieldDao.insertTableFields(tableFields);
                    }
                } catch (Exception e) {
                    newId = null;
                    Global.getInstance().logError("create table error", e);
                    transactionStatus.setRollbackOnly();
                }
                return newId;
            }
        });
    }

    @Override
    public boolean modifyFeepTable(FeepTable feepTable, List<FeepTableField> tableFields) throws Exception {
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                try {
                    //1.findTable Info
                    FeepTable oldTableInfo = feepTableDao.getTableById(feepTable.getId());
                    if (null == oldTableInfo) return false;
                    //2.findFields Info
                    List<FeepTableField> oldTableFields = feepTableFieldDao.getFeepTableFieldByTableId(feepTable.getId());
                    //3.compare tableName
                    //4.modify table
                    //5.foreach compare field and modify
                    return true;
                } catch (Exception e) {
                    Global.getInstance().logError("create table error", e);
                    transactionStatus.setRollbackOnly();
                    return false;
                }
            }
        });
    }


    @Override
    public EntityBeanSet findFeepTableList(FeepQueryBean queryBean) throws Exception {
        try {
            List<FeepTable> list = feepTableDao.queryFeepTable(queryBean);
            List<EntityBean> entityBeans = new LinkedList<EntityBean>();
            if (FeepUtil.isNull(list)) {
                for (FeepTable feepTable : list) {
                    EntityBean bean = new EntityBean();
                    bean.set("id", feepTable.getId());
                    bean.set("name", feepTable.getName());
                    bean.set("datasourceid", feepTable.getDatasourceid());
                    bean.set("description", feepTable.getDescription());
                    bean.set("showname", feepTable.getShowname());
                    bean.set("tabletype", feepTable.getTabletype());
                    entityBeans.add(bean);
                }
            }
            EntityBeanSet ebs = new EntityBeanSet(entityBeans);
            ebs.setModuleName(queryBean.getModuleName());
            int count = feepTableDao.countFeepTable();
            Page page = new Page();
            page.setPageIndex(queryBean.getPageIndex());
            page.setPageSize(queryBean.getPageSize());
            page.setTotalCount(count);
            page.setTotalPageNum(count / queryBean.getPageSize() + 1);
            ebs.setPage(page);
            return ebs;
        } catch (TableException e) {
            throw new Exception("findFeepTableList error", e);
        }
    }

    @Override
    public FeepTable findFeepTableById(String id) throws Exception {
        try {
            return feepTableDao.getTableById(id);
        } catch (TableException e) {
            throw new Exception("findFeepTableById error", e);
        }
    }

    @Override
    public boolean modifyFeepTable(FeepTable feepTable) throws Exception {
        try {
            return feepTableDao.modifyTableInfo(feepTable);
        } catch (TableException e) {
            throw new Exception("modifyFeepTable error", e);
        }
    }

    @Override
    public boolean deleteFeepTable(String id) throws Exception {
        try {
            return feepTableDao.deleteTableById(id);
        } catch (TableException e) {
            throw new Exception("deleteFeepTable error", e);
        }
    }

    @Override
    public EntityBeanSet findFeepTableFieldsByTableId(String tableId) throws Exception {
        try {
            List<FeepTableField> list = feepTableFieldDao.getFeepTableFieldByTableId(tableId);
            List<EntityBean> entityBeans = new LinkedList<EntityBean>();
            if (FeepUtil.isNull(list)) {
                for (FeepTableField feepTableField : list) {
                    EntityBean bean = new EntityBean();
                    bean.set("id", feepTableField.getId());
                    bean.set("name", feepTableField.getName());
                    bean.set("showname", feepTableField.getShowname());
                    bean.set("datatype", feepTableField.getDatatype());
                    bean.set("range", feepTableField.getRange());
                    bean.set("precision", feepTableField.getPrecision());
                    bean.set("isnotnull", feepTableField.isNotnull());
                    bean.set("isunique", feepTableField.isUnique());
                    bean.set("tableid", feepTableField.getTableid());
                    entityBeans.add(bean);
                }
            }
            return new EntityBeanSet(entityBeans);
        } catch (TableException e) {
            throw new Exception("findFeepTableFieldsByTableId error, tableId:" + tableId, e);
        }
    }

}
