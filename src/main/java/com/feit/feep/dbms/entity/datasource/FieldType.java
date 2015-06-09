package com.feit.feep.dbms.entity.datasource;


import com.feit.feep.dbms.entity.module.FeepTableField;

import java.util.List;

/**
 * Created by ZhangGang on 2015/6/9 0009.
 */
public enum FieldType {
    Text("文本"), TextArea("文本域"), Select("下拉选"), Integer("整数"), Decimal("小数"), Blog("大对象"), Date("日期"),
    Datetime("日期时间"), Time("时间"), Attachment("附件");

    private String showName;

    FieldType(String showName) {
        this.showName = showName;
    }

    public String getSql(Dialect dialect, int range, int precision) {
        //character varying(50)
        String sql = null;
        switch (dialect) {
            case POSTGRESQL:
                switch (this) {
                    case Text:
                    case TextArea:
                    case Select:
                        sql = " character varying(" + range + ") ";
                        break;
                    case Integer:
                        sql = " bigint ";
                        break;
                    case Decimal:
                        sql = " numeric(" + range + "," + precision + ") ";
                        break;
                    case Blog:
                        sql = " bytea ";
                        break;
                    case Date:
                        sql = " date ";
                        break;
                    case Datetime:
                        sql = " timestamp without time zone ";
                        break;
                    case Time:
                        sql = " time without time zone ";
                        break;
                    case Attachment:
                        break;
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
        return sql;
    }

}
