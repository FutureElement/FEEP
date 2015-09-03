package com.feit.feeptest.dbms.dao;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.core.Global;
import com.feit.feep.dbms.dao.IFeepTableDao;
import com.feit.feep.dbms.entity.datasource.FieldType;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.dbms.entity.query.QueryParameter;
import com.feit.feep.util.json.FeepJsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ZhangGang on 2015/6/21 0021.
 */
public class TestTableDao extends FeepJUnit {
    @Autowired
    private IFeepTableDao feepTableDao;

    @Test
    public void testCreateTable() throws Exception {
        FeepTable feepTable = new FeepTable();
        feepTable.setName("feep_tablefield2");
        List<FeepTableField> feepTableFields = new LinkedList<FeepTableField>();
        feepTableFields.add(new FeepTableField("100", "id", "主键", FieldType.Text.name(), 50, 0, true, false, "123"));
        feepTableFields.add(new FeepTableField("100", "name", "字段名称", FieldType.Text.name(), 50, 0, true, true, "123"));
        feepTableFields.add(new FeepTableField("101", "showname", "显示名", FieldType.Text.name(), 50, 0, true, false, "123"));
        feepTableFields.add(new FeepTableField("102", "datatype", "数据类型", FieldType.TextArea.name(), 50, 0, true, false, "123"));
        feepTableFields.add(new FeepTableField("103", "range", "范围", FieldType.Integer.name(), 10, 0, false, false, "123"));
        feepTableFields.add(new FeepTableField("104", "precision", "精度", FieldType.Integer.name(), 10, 0, false, false, "123"));
        feepTableFields.add(new FeepTableField("105", "isnotnull", "是否非空", FieldType.Boolean.name(), 5, 0, false, false, "123"));
        feepTableFields.add(new FeepTableField("105", "isunique", "是否唯一", FieldType.Boolean.name(), 5, 0, false, false, "123"));
        feepTableFields.add(new FeepTableField("105", "tableid", "数据表id", FieldType.Text.name(), 50, 0, true, false, "123"));
        feepTableDao.createTable(feepTable, feepTableFields);
        feepTableDao.modifyTableName("feep_tablefield2", "feep_tablefield3");
        feepTableDao.removeTable("feep_tablefield3");
    }

    @Test
    public void test() throws Exception {
        feepTableDao.deleteTableById("123456");
        FeepTable feepTable = new FeepTable();
        feepTable.setId("123456");
        feepTable.setName("feep_tablefield2");
        feepTable.setTabletype("1");
        feepTable.setShowname("tttt");
        feepTable.setDatasourceid("0");
        feepTableDao.insertFeepTable(feepTable);
        Global.getInstance().logInfo(feepTableDao.getTableById(feepTable.getId()).getShowname());
        feepTable.setName("feep_tablefield3");
        feepTableDao.modifyTableInfo(feepTable);
        feepTableDao.deleteTableById("123456");
    }


    @Test
    public void testQueryFeepTable() throws Exception {
        FeepQueryBean queryBean = new FeepQueryBean();
        queryBean.setModuleName("feep_table");
        queryBean.setPageIndex(1);
        queryBean.setPageSize(20);
        List<QueryParameter> list = new LinkedList<QueryParameter>();
        list.add(new QueryParameter("name", "test"));
        list.add(new QueryParameter("tabletype", "1"));
        queryBean.setQueryParameters(list);
        Global.getInstance().logInfo(FeepJsonUtil.toJson(feepTableDao.queryFeepTable(queryBean)));
    }

    @Test
    public void count() throws Exception {
        int a = feepTableDao.countFeepTable();
        Global.getInstance().logInfo(a + "");
    }
}
