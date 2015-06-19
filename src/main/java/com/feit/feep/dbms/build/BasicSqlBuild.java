package com.feit.feep.dbms.build;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.entity.datasource.Dialect;
import com.feit.feep.dbms.entity.datasource.FieldType;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.dbms.entity.query.QueryParameter;
import com.feit.feep.dbms.entity.query.SortField;
import com.feit.feep.util.FeepUtil;

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

    public String getQuerySQL(FeepQueryBean queryBean) {
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
                        stringBuilder.append(queryParameters.get(i).getFieldName());
                        stringBuilder.append(queryParameters.get(i).getCondition().getCndSQL());
                        String value = "";
                        switch (queryParameters.get(i).getCondition()) {
                            case LIKE:
                            case NOTLIKE:
                                value = "'%#value#%'";
                                break;
                            case LEFTLIKE:
                                value = "'#value#%'";
                                break;
                            case RIGHTLIKE:
                                value = "'%#value#'";
                                break;
                            default:
                                value = "'#value#'";
                                break;
                        }
                        stringBuilder.append(value.replace("#value#", queryParameters.get(i).getParameterValue()));
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
                    int[] pages = getPageStartAndEnd(pageIndex, pageSize);
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
        return stringBuilder.toString();
    }

    public static int[] getPageStartAndEnd(int pageIndex, int pageSize) {
        int start = pageSize * (pageIndex - 1);
        int end = start + pageSize - 1;
        return new int[]{start, end};
    }

    public static String convertArrayToSqlString(String[] datas) {
        if (null == datas || datas.length == 0) {
            return "''";
        }
        StringBuilder buff = new StringBuilder();
        for (int i = 0; i < datas.length; i++) {
            buff.append("'");
            buff.append(datas[i]);
            buff.append("'");
            if (i != (datas.length - 1)) {
                buff.append(",");
            }
        }
        return buff.toString();
    }
}

