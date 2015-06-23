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
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    public String createFeepTable(FeepTable table, List<FeepTableField> fields) throws Exception {
        final FeepTable feepTable = table;
        final List<FeepTableField> tableFields = fields;
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
                        for (FeepTableField feepTableField : tableFields) {
                            feepTableField.setTableid(newId);
                        }
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
    public boolean modifyFeepTable(FeepTable table, List<FeepTableField> fields) throws Exception {
        final FeepTable feepTable = table;
        final List<FeepTableField> tableFields = fields;
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
                    String oldName = oldTableInfo.getName();
                    if (!oldName.equals(feepTable.getName())) {
                        feepTableDao.modifyTableName(oldName, feepTable.getName());
                    }
                    //4.modify table
                    feepTableDao.modifyTableInfo(feepTable);
                    //5.foreach compare field and modify
                    Map<String, FeepTableField> oldTableFieldMap = convertFeepTableFieldsToMap(oldTableFields);
                    Map<String, FeepTableField> newTableFieldMap = convertFeepTableFieldsToMap(tableFields);
                    if (!FeepUtil.isNull(oldTableFields)) {
                        List<String> deleteIds = new LinkedList<String>();
                        for (FeepTableField oldField : oldTableFields) {
                            //remove db fields
                            if (null == newTableFieldMap.get(oldField.getId())) {
                                deleteIds.add(oldField.getId());
                                feepTableFieldDao.removeTableColumn(feepTable.getName(), oldField.getName());
                            }
                        }
                        //delete fieldinfo
                        if (!FeepUtil.isNull(deleteIds)) {
                            feepTableFieldDao.deleteTableFieldsByIds(deleteIds.toArray(new String[deleteIds.size()]));
                        }
                    }
                    if (!FeepUtil.isNull(tableFields)) {
                        List<FeepTableField> newFieldList = new LinkedList<FeepTableField>();
                        for (FeepTableField newField : tableFields) {
                            FeepTableField oldField = oldTableFieldMap.get(newField.getId());
                            //add field
                            if (null == oldField) {
                                feepTableFieldDao.addTableColumn(feepTable.getName(), newField);
                                newFieldList.add(newField);
                            } else {
                                boolean isChange = false;
                                // is modify name
                                if (!oldField.getName().equals(newField.getName())) {
                                    isChange = true;
                                    feepTableFieldDao.modifyTableColumnName(feepTable.getName(), oldField.getName(), newField.getName());
                                }
                                // is change notnull
                                if (oldField.isNotnull() != newField.isNotnull()) {
                                    isChange = true;
                                    if (newField.isNotnull()) {
                                        feepTableFieldDao.addNotNullConstraint(feepTable.getName(), newField.getName());
                                    } else {
                                        feepTableFieldDao.removeNotNullConstraint(feepTable.getName(), newField.getName());
                                    }
                                }
                                // is change unique
                                if (oldField.isUnique() != newField.isUnique()) {
                                    isChange = true;
                                    if (newField.isUnique()) {
                                        feepTableFieldDao.addUniqueConstraint(feepTable.getName(), newField.getName());
                                    } else {
                                        feepTableFieldDao.removeUniqueConstraint(feepTable.getName(), newField.getName());
                                    }
                                }
                                // is range change
                                if (oldField.getRange() != newField.getRange() || oldField.getPrecision() != newField.getPrecision()) {
                                    isChange = true;
                                    feepTableFieldDao.modifyTableColumnRange(feepTable.getName(), newField);
                                }
                                if (isChange) {
                                    //modify field info
                                    feepTableFieldDao.updateTableFieldInfo(newField);
                                }
                            }
                        }
                        if (!FeepUtil.isNull(newFieldList)) {
                            feepTableFieldDao.insertTableFields(newFieldList);
                        }
                    }
                    return true;
                } catch (Exception e) {
                    Global.getInstance().logError("create table error", e);
                    transactionStatus.setRollbackOnly();
                    return false;
                }
            }
        });
    }

    private Map<String, FeepTableField> convertFeepTableFieldsToMap(List<FeepTableField> feepTableFields) {
        Map<String, FeepTableField> map = new HashMap<String, FeepTableField>();
        if (!FeepUtil.isNull(feepTableFields)) {
            for (FeepTableField feepTableField : feepTableFields) {
                map.put(feepTableField.getId(), feepTableField);
            }
        }
        return map;
    }

    @Override
    public EntityBeanSet findFeepTableList(FeepQueryBean queryBean) throws Exception {
        try {
            List<FeepTable> list = feepTableDao.queryFeepTable(queryBean);
            List<EntityBean> entityBeans = new LinkedList<EntityBean>();
            if (!FeepUtil.isNull(list)) {
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
    public boolean deleteFeepTable(String tableId) throws Exception {
        final String id = tableId;
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                try {
                    //1.delete tableinfo
                    feepTableDao.deleteTableById(id);
                    //2.delete tablefield
                    feepTableFieldDao.deleteTableFieldsByTableId(id);
                    return true;
                } catch (Exception e) {
                    Global.getInstance().logError("deleteFeepTable table error", e);
                    transactionStatus.setRollbackOnly();
                    return false;
                }
            }
        });
    }

    @Override
    public EntityBeanSet findFeepTableFieldsByTableId(String tableId) throws Exception {
        try {
            List<FeepTableField> list = feepTableFieldDao.getFeepTableFieldByTableId(tableId);
            List<EntityBean> entityBeans = new LinkedList<EntityBean>();
            if (!FeepUtil.isNull(list)) {
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

    @Override
    public boolean removeFeepTable(String tableName) throws Exception {
        try {
            feepTableDao.removeTable(tableName);
            return true;
        } catch (Exception e) {
            throw new Exception("removeFeepTable table error", e);
        }
    }

}