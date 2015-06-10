package com.feit.feep.dbms.entity.datasource;


/**
 * Created by ZhangGang on 2015/6/9 0009.
 */
public enum FieldType {
    Text("文本", 100), TextArea("文本域", 500), Select("下拉选", 50), Integer("整数", 0), Decimal("小数", 10),
    Boolean("布尔", 5), Blog("大对象?", 2000), Date("日期", 0),
    Datetime("日期时间", 0), Time("时间", 0), Attachment("附件", 0);

    private String showName;
    private int defaultRange;

    FieldType(String showName, int defaultRange) {
        this.showName = showName;
        this.defaultRange = defaultRange;
    }

    public String getShowName() {
        return showName;
    }

    public int getDefaultRange() {
        return defaultRange;
    }

    public String getSql(Dialect dialect, int range, int precision) {
        String sql = null;
        switch (dialect) {
            case POSTGRESQL:
                switch (this) {
                    case Text:
                    case TextArea:
                    case Select:
                    case Boolean:
                    case Attachment:
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
