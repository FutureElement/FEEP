package com.feit.feep.dbms.entity.datasource;


import com.feit.feep.dbms.entity.module.FeepTableField;

import java.util.List;

/**
 * Created by ZhangGang on 2015/6/9 0009.
 */
public enum FieldType {
    Text("�ı�"), TextArea("�ı���"), Select("����ѡ"), Integer("����"), Decimal("С��"), Blog("�����"), Date("����"),
    Datetime("����ʱ��"), Time("ʱ��"), Attachment("����");

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
