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
        dialect = Global.getInstance().getFeepConfig().getDBInfo().getDialect();
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
                    for (int i = 0; i < tableFields.size(); i++) {
                        stringBuilder.append(tableFields.get(i).getName());
                        stringBuilder.append(FieldType.valueOf(tableFields.get(i).getDatatype()).getSql(dialect, tableFields.get(i).getRange(), tableFields.get(i).getPrecision()));
                        if (tableFields.get(i).getNotnull() == 0) {
                            stringBuilder.append(" NOT NULL");
                        }
                        if (i != tableFields.size() - 1) {
                            stringBuilder.append(",");
                        }
                    }
                }
                for (FeepTableField tableField : tableFields) {
                    stringBuilder.append(" CONSTRAINT ");
                    stringBuilder.append(feepTable.getName());
                    stringBuilder.append("_");
                    stringBuilder.append(tableField.getName());
                    stringBuilder.append("_unique_key UNIQUE (");
                    stringBuilder.append(tableField.getName());
                    stringBuilder.append("), ");
                }
                stringBuilder.append(" CONSTRAINT ");
                stringBuilder.append(feepTable.getName());
                stringBuilder.append("_id");
                stringBuilder.append(" PRIMARY KEY (id) ");
                stringBuilder.append(" ) WITH ( OIDS=FALSE ); ");
                stringBuilder.append(" ALTER TABLE ");
                stringBuilder.append(feepTable.getName());
                stringBuilder.append(" OWNER TO postgres;");
                return null;
            case ORACLE:
                //TODO
                return null;
            case MYSQL:
                //TODO
                return null;
            case SQLSERVER:
                //TODO
                return null;
            default:
                return null;
        }
    }

}
