package com.feit.feeptest.dbms.service;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.core.Global;
import com.feit.feep.dbms.dao.IFeepTableDao;
import com.feit.feep.dbms.entity.EntityBean;
import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.datasource.FieldType;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.dbms.service.ITableManagementService;
import com.feit.feep.util.FeepUtil;
import com.feit.feep.util.json.FeepJsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ZhangGang on 2015/6/21 0021.
 */
public class TestTableManagementService extends FeepJUnit {

    @Autowired
    private ITableManagementService tableManagementService;

    @Autowired
    private IFeepTableDao feepTableDao;

    @Test
    public void testCreateFeepTable() throws Exception {
        FeepTable feepTable = new FeepTable();
        feepTable.setName("feep_tablefield_test");
        feepTable.setTabletype("1");
        feepTable.setShowname("feep_tablefield_test");
        feepTable.setDescription("aaa");
        feepTable.setDatasourceid("0");
        List<FeepTableField> feepTableFields = new LinkedList<FeepTableField>();
        feepTableFields.add(new FeepTableField("t099", "id", "主键", FieldType.Text.name(), 50, 0, true, false, "123"));
        feepTableFields.add(new FeepTableField("t100", "name", "字段名称", FieldType.Text.name(), 50, 0, true, true, "123"));
        feepTableFields.add(new FeepTableField("t101", "showname", "显示名", FieldType.Text.name(), 50, 0, true, false, "123"));
        feepTableFields.add(new FeepTableField("t102", "datatype", "数据类型", FieldType.TextArea.name(), 50, 0, true, false, "123"));
        feepTableFields.add(new FeepTableField("t103", "range", "范围", FieldType.Integer.name(), 10, 0, false, false, "123"));
        feepTableFields.add(new FeepTableField("t104", "precision", "精度", FieldType.Integer.name(), 10, 0, false, false, "123"));
        feepTableFields.add(new FeepTableField("t105", "isnotnull", "是否非空", FieldType.Boolean.name(), 5, 0, false, false, "123"));
        feepTableFields.add(new FeepTableField("t106", "isunique", "是否唯一", FieldType.Boolean.name(), 5, 0, false, false, "123"));
        feepTableFields.add(new FeepTableField("t107", "tableid", "数据表id", FieldType.Text.name(), 50, 0, true, false, "123"));
        String tableId = tableManagementService.createFeepTable(feepTable, feepTableFields);
        testFindFeepTableList();
        testFindFeepTableById(tableId);
        testFindFeepTableFieldsByTableId(tableId);
        FeepTable mfeepTable = new FeepTable();
        mfeepTable.setTabletype("1");
        mfeepTable.setDescription("aaa");
        mfeepTable.setDatasourceid("0");
        mfeepTable.setId(tableId);
        mfeepTable.setName("feep_tablefield_test2");
        mfeepTable.setShowname("aaa");
        mfeepTable.setId(tableId);
        feepTableFields.remove(feepTableFields.size() - 1);
        feepTableFields.get(0).setName("aaa");
        feepTableFields.get(2).setNotnull(false);
        feepTableFields.get(2).setRange(50);
        feepTableFields.get(6).setUnique(true);
        feepTableFields.get(6).setNotnull(true);
        feepTableFields.add(new FeepTableField("t111", "test", "test", FieldType.Text.name(), 50, 0, true, false, tableId));
        testModifyFeepTable(mfeepTable, feepTableFields);
        tableManagementService.deleteFeepTable(tableId);
        feepTableDao.removeTable(mfeepTable.getName());
    }

    private void testModifyFeepTable(FeepTable feepTable, List<FeepTableField> feepTableFields) throws Exception {
        tableManagementService.modifyFeepTable(feepTable, feepTableFields);
    }

    private void testFindFeepTableList() throws Exception {
        FeepQueryBean feepQueryBean = new FeepQueryBean();
        feepQueryBean.setPageIndex(1);
        feepQueryBean.setPageSize(20);
        List<String> list = new LinkedList<String>();
        list.add("id");
        list.add("name");
        feepQueryBean.setFields(list);
        EntityBeanSet ebs = tableManagementService.findFeepTableList(feepQueryBean);
        Global.getInstance().logInfo(FeepJsonUtil.toJson(ebs));
    }

    private void testFindFeepTableById(String tableId) throws Exception {
        FeepTable feepTable = tableManagementService.findFeepTableById(tableId);
        Global.getInstance().logInfo(feepTable.getName());
    }

    private void testFindFeepTableFieldsByTableId(String tableId) throws Exception {
        EntityBeanSet ebs = tableManagementService.findFeepTableFieldsByTableId(tableId);
        Global.getInstance().logInfo(ebs.toString());
        if (!FeepUtil.isNull(ebs)) {
            for (int i = 0; i < ebs.size(); i++) {
                EntityBean bean = ebs.get(i);
                Global.getInstance().logInfo(bean.toString());
                Global.getInstance().logInfo(ebs.get(i).getString("id"));
            }
        }
    }

}
