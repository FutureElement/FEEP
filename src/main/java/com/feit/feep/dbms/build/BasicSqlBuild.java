package com.feit.feep.dbms.build;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.entity.datasource.Dialect;
import com.feit.feep.dbms.entity.datasource.FieldType;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;
import com.feit.feep.dbms.entity.query.*;
import com.feit.feep.util.FeepUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ZhangGang on 2015/6/9 0009.
 */
public class BasicSqlBuild {

    private Dialect dialect;

    public BasicSqlBuild() {
        try {
            dialect = Global.getInstance().getFeepConfig().getDBInfo().getDialect();
        } catch (Exception e) {
            Global.getInstance().logError("Get Dialect Error", e);
        }
    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }

    public String getCreateSQL(FeepTable feepTable, List<FeepTableField> tableFields) {
        StringBuilder stringBuilder = new StringBuilder();
        switch (dialect) {
            case POSTGRESQL:
                stringBuilder.append("CREATE TABLE ");
                stringBuilder.append(feepTable.getName());
                stringBuilder.append("( ");
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
                            stringBuilder.append(getUniqueConstraintName(feepTable.getName(), tableField.getName()));
                            stringBuilder.append(" UNIQUE (");
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

    public String getRemoveTableSql(String tableName) {
        String sql = null;
        switch (dialect) {
            case POSTGRESQL:
                sql = "DROP TABLE " + tableName;
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
        return sql;
    }

    private String getUniqueConstraintName(String tableName, String fieldName) {
        return tableName + "_" + fieldName + "_unique_key";
    }

    public String getAddColumnSQL(String tableName, String fieldName, FieldType type, int range, int precision) {
        String sql = null;
        switch (dialect) {
            case POSTGRESQL:
                sql = "ALTER TABLE " + tableName + " ADD COLUMN " + fieldName + " " + type.getSql(dialect, range, precision);
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
        return sql;
    }

    public String getAddNotNullConstraint(String tableName, String fieldName) {
        String sql = null;
        switch (dialect) {
            case POSTGRESQL:
                sql = "ALTER TABLE " + tableName + " ALTER COLUMN " + fieldName + " SET NOT NULL";
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
        return sql;
    }

    public String getRemoveNotNullConstraint(String tableName, String fieldName) {
        String sql = null;
        switch (dialect) {
            case POSTGRESQL:
                sql = "ALTER TABLE " + tableName + " ALTER COLUMN " + fieldName + " DROP NOT NULL";
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
        return sql;
    }

    public String getRemoveColumnSQL(String tableName, String fieldName) {
        String sql = null;
        switch (dialect) {
            case POSTGRESQL:
                sql = "ALTER TABLE " + tableName + " DROP COLUMN " + fieldName;
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
        return sql;
    }

    public String getModifyTableColumnName(String tableName, String fieldName, String newName) {
        String sql = null;
        switch (dialect) {
            case POSTGRESQL:
                sql = "ALTER TABLE " + tableName + " RENAME " + fieldName + "  TO " + newName;
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
        return sql;
    }

    public String getAddUniqueConstraintSQL(String tableName, String fieldName) {
        String sql = null;
        switch (dialect) {
            case POSTGRESQL:
                sql = "ALTER TABLE " + tableName + " ADD CONSTRAINT " + getUniqueConstraintName(tableName, fieldName) + " UNIQUE (" + fieldName + ")";
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
        return sql;
    }

    public String getRemoveUniqueConstraintSQL(String tableName, String fieldName) {
        String sql = null;
        switch (dialect) {
            case POSTGRESQL:
                sql = "ALTER TABLE " + tableName + " DROP CONSTRAINT " + getUniqueConstraintName(tableName, fieldName);
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
        return sql;
    }

    public String getModifyTableColumnRangeSQL(String tableName, String fieldName, FieldType fieldType, int range, int precision) {
        String sql = null;
        switch (dialect) {
            case POSTGRESQL:
                sql = "ALTER TABLE " + tableName + " ALTER COLUMN " + fieldName + " TYPE " + fieldType.getSql(dialect, range, precision);
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
        return sql;
    }

    public String getModifyTableName(String tableName, String newName) {
        String sql = null;
        switch (dialect) {
            case POSTGRESQL:
                sql = "ALTER TABLE " + tableName + " RENAME TO " + newName;
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
        return sql;
    }

    public FeepSQL getQuerySQL(FeepQueryBean queryBean) {
        FeepSQL sql = new FeepSQL();
        List<Object> args = new LinkedList<Object>();
        StringBuilder stringBuilder = new StringBuilder();
        switch (dialect) {
            case POSTGRESQL:
                stringBuilder.append("SELECT ");
                List<String> fields = queryBean.getFields();
                if (FeepUtil.isNull(fields)) {
                    stringBuilder.append("* ");
                } else {
                    for (int i = 0; i < fields.size(); i++) {
                        stringBuilder.append(fields.get(i));
                        if (i != fields.size() - 1) {
                            stringBuilder.append(",");
                        }
                    }
                }
                stringBuilder.append(" FROM ");
                stringBuilder.append(queryBean.getModuleName());
                List<QueryParameter> queryParameters = queryBean.getQueryParameters();
                if (!FeepUtil.isNull(queryParameters)) {
                    stringBuilder.append(" WHERE ");
                    for (int i = 0; i < queryParameters.size(); i++) {
                        if (i != 0) {
                            stringBuilder.append(" AND ");
                        }
                        Condition cnd = queryParameters.get(i).getCondition();
                        stringBuilder.append(queryParameters.get(i).getFieldName());
                        stringBuilder.append(cnd.getCndSQL());
                        String fieldValue = queryParameters.get(i).getParameterValue();
                        String value;
                        switch (cnd) {
                            case LIKE:
                            case NOTLIKE:
                                value = "%" + fieldValue + "%";
                                break;
                            case LEFTLIKE:
                                value = fieldValue + "%";
                                break;
                            case RIGHTLIKE:
                                value = "%" + fieldValue;
                                break;
                            default:
                                value = fieldValue;
                                break;
                        }
                        stringBuilder.append(" ? ");
                        args.add(value);
                    }
                }
                List<SortField> sortFields = queryBean.getSortFields();
                if (!FeepUtil.isNull(sortFields)) {
                    stringBuilder.append(" ORDER BY ");
                    for (int i = 0; i < sortFields.size(); i++) {
                        stringBuilder.append(sortFields.get(i).getFieldName());
                        stringBuilder.append(sortFields.get(i).isAsc() ? " ASC " : " DESC ");
                        if (i != sortFields.size() - 1) {
                            stringBuilder.append(",");
                        }
                    }
                }
                int pageIndex = queryBean.getPageIndex();
                int pageSize = queryBean.getPageSize();
                if (pageIndex > 0 && pageSize > 0) {
                    int[] pages = GeneratorSqlBuild.getPageStartAndEnd(pageIndex, pageSize);
                    stringBuilder.append(" LIMIT ");
                    stringBuilder.append(pageSize);
                    stringBuilder.append(" OFFSET ");
                    stringBuilder.append(pages[0]);
                }
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
        sql.setSql(stringBuilder.toString());
        if (!FeepUtil.isNull(args)) {
            sql.setParams(args.toArray(new Object[args.size()]));
        }
        return sql;
    }

}

