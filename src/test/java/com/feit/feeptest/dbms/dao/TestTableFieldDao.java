package com.feit.feeptest.dbms.dao;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.dbms.dao.IFeepTableDao;
import com.feit.feep.dbms.dao.IFeepTableFieldDao;
import com.feit.feep.dbms.entity.datasource.FieldType;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ZhangGang on 2015/6/21 0021.
 */
public class TestTableFieldDao extends FeepJUnit {
    @Autowired
    private IFeepTableDao feepTableDao;
    @Autowired
    private IFeepTableFieldDao feepTableFieldDao;

    @Test
    public void testInsertTableField() throws Exception {
        feepTableFieldDao.deleteTableFieldById("123123");
        FeepTableField feepTableField = new FeepTableField();
        feepTableField.setId("123123");
        feepTableField.setTableid("111");
        feepTableField.setUnique(false);
        feepTableField.setNotnull(false);
        feepTableField.setDatatype(FieldType.Text.name());
        feepTableField.setShowname("aaa");
        feepTableField.setName("aaa");
        feepTableField.setRange(200);
        feepTableFieldDao.insertTableField(feepTableField);
        feepTableFieldDao.deleteTableFieldById("123123");
    }

    @Test
    public void testInsertTableFields() throws Exception {
        List<FeepTableField> feepTableFields = new LinkedList<FeepTableField>();
        for (int i = 0; i < 15; i++) {
            FeepTableField feepTableField = new FeepTableField();
            feepTableField.setTableid("111");
            feepTableField.setUnique(false);
            feepTableField.setNotnull(false);
            feepTableField.setDatatype(FieldType.Text.name());
            feepTableField.setShowname("aaaa" + i);
            feepTableField.setName("aaaa" + i);
            feepTableField.setRange(200);
            feepTableFields.add(feepTableField);
        }
        String[] ids = feepTableFieldDao.insertTableFields(feepTableFields);
        List<FeepTableField> list = feepTableFieldDao.getFeepTableFieldByTableId("111");
        FeepTableField feepTableField = feepTableFieldDao.findFeepTableFieldById(list.get(0).getId());
        Assert.assertEquals(list.get(0).getName(), feepTableField.getName());
        boolean ret = feepTableFieldDao.deleteTableFieldsByIds(ids);
        Assert.assertTrue(ret);
    }

    @Test
    public void testColumnOperate() throws Exception {
        String tableName = "feep_tablefieldstest";
        FeepTable feepTable = new FeepTable();
        feepTable.setName(tableName);
        List<FeepTableField> feepTableFields = new LinkedList<FeepTableField>();
        feepTableFields.add(new FeepTableField("99", "id", "主键", FieldType.Text.name(), 50, 0, true, false, "123"));
        feepTableFields.add(new FeepTableField("100", "name", "字段名称", FieldType.Text.name(), 50, 0, false, false, "123"));
        feepTableFields.add(new FeepTableField("101", "showname", "显示名", FieldType.Text.name(), 50, 0, false, false, "123"));
        feepTableFields.add(new FeepTableField("102", "datatype", "数据类型", FieldType.TextArea.name(), 50, 0, false, false, "123"));
        feepTableFields.add(new FeepTableField("103", "range", "范围", FieldType.Integer.name(), 10, 0, false, false, "123"));
        feepTableFields.add(new FeepTableField("104", "precision", "精度", FieldType.Integer.name(), 10, 0, false, false, "123"));
        feepTableFields.add(new FeepTableField("105", "isnotnull", "是否非空", FieldType.Boolean.name(), 5, 0, false, false, "123"));
        feepTableFields.add(new FeepTableField("105", "isunique", "是否唯一", FieldType.Boolean.name(), 5, 0, false, false, "123"));
        feepTableFields.add(new FeepTableField("105", "tableid", "数据表id", FieldType.Text.name(), 50, 0, false, false, "123"));
        feepTable.setFields(feepTableFields);
        feepTableDao.createTable(feepTable);
        feepTableFieldDao.addTableColumn(tableName, new FeepTableField("666", "test", "test", FieldType.Text.name(), 50, 0, false, false, "123"));
        feepTableFieldDao.addNotNullConstraint(tableName, "test");
        feepTableFieldDao.addUniqueConstraint(tableName, "test");
        feepTableFieldDao.removeNotNullConstraint(tableName, "test");
        feepTableFieldDao.removeUniqueConstraint(tableName, "test");
        feepTableFieldDao.modifyTableColumnRange(tableName, new FeepTableField("666", "test", "test", FieldType.Text.name(), 150, 0, false, false, "123"));
        feepTableFieldDao.modifyTableColumnName(tableName, "test", "test2");
        feepTableFieldDao.removeTableColumn(tableName, "test2");
        feepTableDao.removeTable(tableName);
    }
}
