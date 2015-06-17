package com.feit.feep.dbms.service;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.dao.ITableDao;
import com.feit.feep.dbms.entity.EntityBean;
import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.dbms.entity.query.Page;
import com.feit.feep.exception.dbms.TableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * 数据表管理service
 * Created by ZhangGang on 2015/6/15 0015.
 */
@Service
public class TableManagementService implements ITableManagementService {

    @Autowired
    private ITableDao basicTableDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public String createFeepTable(FeepTable feepTable, List<FeepTableField> tableFields) throws Exception {
        return transactionTemplate.execute(transactionStatus -> {
            String newId;
            try {
                //1.create table
                basicTableDao.createTable(feepTable, tableFields);
                //2.insert to feeptable
                newId = basicTableDao.insertFeepTable(feepTable);
                //3.insert to feeptableField
                if (null != tableFields && !tableFields.isEmpty()) {
                    //TODO 调用 tableFieldDAO 最后写在Service中
                }
            } catch (Exception e) {
                newId = null;
                Global.getInstance().logError("create table error", e);
                transactionStatus.setRollbackOnly();
            }
            return newId;
        });
    }


    @Override
    public EntityBeanSet findFeepTableList(FeepQueryBean bean) throws Exception {
        try {
            List<EntityBean> list = basicTableDao.queryFeepTable(bean);
            EntityBeanSet ebs = new EntityBeanSet(list);
            ebs.setModuleName(bean.getModuleName());
            int count = basicTableDao.countFeepTable();
            Page page = new Page();
            page.setPageIndex(bean.getPageIndex());
            page.setPageSize(bean.getPageSize());
            page.setTotalCount(count);
            page.setTotalPageNum(count / bean.getPageSize() + 1);
            ebs.setPage(page);
            return ebs;
        } catch (TableException e) {
            throw new Exception("findFeepTableList error", e);
        }
    }

    @Override
    public FeepTable findFeepTableById(String id) throws Exception {
        try {
            return basicTableDao.getTableById(id);
        } catch (TableException e) {
            throw new Exception("findFeepTableById error", e);
        }
    }

    @Override
    public boolean modifyFeepTable(FeepTable feepTable) throws Exception {
        try {
            return basicTableDao.modifyTable(feepTable);
        } catch (TableException e) {
            throw new Exception("modifyFeepTable error", e);
        }
    }

    @Override
    public boolean deleteFeepTable(String id) throws Exception {
        try {
            return basicTableDao.deleteTableById(id);
        } catch (TableException e) {
            throw new Exception("deleteFeepTable error", e);
        }
    }
}
