package com.feit.feeptest.dbms.build;

import java.util.LinkedList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.core.Global;
import com.feit.feep.dbms.build.BasicSqlBuild;
import com.feit.feep.dbms.dao.IFeepTableDao;
import com.feit.feep.dbms.entity.datasource.FieldType;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;

/**
 * Created by ZhangGang on 2015/6/10 0010.
 */
public class TableSQLTest extends FeepJUnit {

    @Autowired
    private IFeepTableDao dao;

    @Test
    public void test() {
        BasicSqlBuild basicSqlBuild = new BasicSqlBuild();
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
        Global.getInstance().logInfo(basicSqlBuild.getCreateSQL(feepTable, feepTableFields));
    }

    @Ignore
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
        dao.createTable(feepTable, feepTableFields);
    }


}
