package com.feit.feep.dbms.service;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.dao.IBasicTableDao;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * Created by ZhangGang on 2015/6/15 0015.
 */
@Service
public class TableManagementService implements ITableManagementService {

    @Autowired
    private IBasicTableDao basicTableDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public String createFeepTable(FeepTable feepTable) {
        return transactionTemplate.execute(new TransactionCallback<String>() {
            @Override
            public String doInTransaction(TransactionStatus transactionStatus) {
                String newId = null;
                try {
                    //1.create table
                    basicTableDao.createTable(feepTable);
                    //2.insert to feeptable
                    newId = basicTableDao.insertFeepTable(feepTable);
                    //3.insert to feeptableField
                    List<FeepTableField> tableFields = feepTable.getTableFields();
                    if (null != tableFields && !tableFields.isEmpty()) {
                        //TODO 调用 tableFieldDAO 最后写在Service中
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
    public List<FeepTable> findFeepTableList(FeepQueryBean bean) {
        return null;
    }

    @Override
    public FeepTable findFeepTableById(String id) {
        return null;
    }

    @Override
    public boolean modifyFeepTable(FeepTable feepTable) {
        return false;
    }

    @Override
    public boolean deleteFeepTable(String id) {
        return false;
    }
}
