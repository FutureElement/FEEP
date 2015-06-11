package com.feit.feeptest.dbms.build;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.core.Global;
import com.feit.feep.dbms.build.TableSqlBuild;
import com.feit.feep.dbms.entity.datasource.FieldType;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;
import com.feit.feep.dbms.table.BasicTableDaoImpl;
import com.feit.feep.util.json.FeepJsonUtil;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ZhangGang on 2015/6/10 0010.
 */
public class TableSQLTest extends FeepJUnit {

    @Autowired
    private BasicTableDaoImpl dao;

    @Test
    public void test() {
        TableSqlBuild tableSqlBuild = new TableSqlBuild();
        FeepTable feepTable = new FeepTable();
        feepTable.setName("feep_tablefield");
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
        feepTable.setTableFields(feepTableFields);
        Global.getInstance().logInfo(tableSqlBuild.getCreateSQL(feepTable));
    }

    @Test
    public void create() throws Exception {
        FeepTable feepTable = new FeepTable();
        feepTable.setName("feep_table");
        List<FeepTableField> feepTableFields = new LinkedList<FeepTableField>();
        feepTableFields.add(new FeepTableField("100", "id", "主键", FieldType.Text.name(), 50, 0, true, false, "123"));
        feepTableFields.add(new FeepTableField("100", "name", "物理表名", FieldType.Text.name(), 50, 0, true, true, "123"));
        feepTableFields.add(new FeepTableField("101", "showname", "显示名", FieldType.Text.name(), 50, 0, true, false, "123"));
        feepTableFields.add(new FeepTableField("102", "tabletype", "类型", FieldType.Text.name(), 50, 0, true, false, "123"));
        feepTableFields.add(new FeepTableField("103", "description", "描述", FieldType.TextArea.name(), 10, 0, false, false, "123"));
        feepTableFields.add(new FeepTableField("104", "datasourceid", "数据源id", FieldType.Text.name(), 10, 0, true, false, "123"));
        feepTable.setTableFields(feepTableFields);
        dao.createTable(feepTable);
    }

    @Test
    public void count() throws Exception {
        int a = dao.countFeepTable();
        Global.getInstance().logInfo(a);
    }

    @Test
    public void getTableByid() throws Exception {
        FeepTable feepTable = dao.getTableById("1000");
        Global.getInstance().logInfo(FeepJsonUtil.toJson(feepTable));
    }

}
