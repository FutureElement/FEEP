package com.feit.feep.dbms.build;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.entity.datasource.Dialect;
import com.feit.feep.dbms.entity.datasource.FieldType;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;

import java.util.List;

/**
 * Created by ZhangGang on 2015/6/9 0009.
 */
public class TableSqlBuild {

    private Dialect dialect;

    public TableSqlBuild() {
        try {
            dialect = Global.getInstance().getFeepConfig().getDBInfo().getDialect();
        } catch (Exception e) {
            Global.getInstance().logError("Get Dialect Error", e);
        }
    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }

    public String getCreateSQL(FeepTable feepTable) {
        StringBuilder stringBuilder = new StringBuilder();
        switch (dialect) {
            case POSTGRESQL:
                stringBuilder.append("CREATE TABLE ");
                stringBuilder.append(feepTable.getName());
                stringBuilder.append("( ");
                List<FeepTableField> tableFields = feepTable.getTableFields();
                if (null != tableFields) {
                    for (FeepTableField tableField : tableFields) {
                        stringBuilder.append(tableField.getName());
                        stringBuilder.append(FieldType.valueOf(tableField.getDatatype()).getSql(dialect, tableField.getRange(), tableField.getPrecision()));
                        if (tableField.isNotnull()) {
                            stringBuilder.append(" NOT NULL");
                        }
                        stringBuilder.append(", ");
                    }
                    for (FeepTableField tableField : tableFields) {
                        if (tableField.isUnique()) {
                            stringBuilder.append(" CONSTRAINT ");
                            stringBuilder.append(feepTable.getName());
                            stringBuilder.append("_");
                            stringBuilder.append(tableField.getName());
                            stringBuilder.append("_unique_key UNIQUE (");
                            stringBuilder.append(tableField.getName());
                            stringBuilder.append("), ");
                        }
                    }
                }
                stringBuilder.append(" CONSTRAINT ");
                stringBuilder.append(feepTable.getName());
                stringBuilder.append("_id");
                stringBuilder.append(" PRIMARY KEY (id) ");
                stringBuilder.append(" )");
                break;
            case ORACLE:
                //TODO
                break;
            case MYSQL:
                //TODO
                break;
            case SQLSERVER:
                //TODO
                break;
            default:
                break;
        }
        return stringBuilder.toString();
    }

}
