package com.feit.feep.dbms.entity.datasource;


/**
 * 字段类型
 * Created by ZhangGang on 2015/6/9 0009.
 */
public enum FieldType {
    Text("文本", 100), TextArea("文本域", 500), Integer("整数"), Decimal("小数", 10, 2),
    Boolean("布尔"), Blog("大对象"), Date("日期"),
    Datetime("日期时间"), Time("时间"), Attachment("附件", 0);

    private String showName;
    private int defaultRange;
    private int precision;

    FieldType(String showName) {
        this.showName = showName;
    }

    FieldType(String showName, int defaultRange) {
        this.showName = showName;
        this.defaultRange = defaultRange;
    }

    FieldType(String showName, int defaultRange, int precision) {
        this.showName = showName;
        this.defaultRange = defaultRange;
        this.precision = precision;
    }

    public String getShowName() {
        return showName;
    }

    public int getDefaultRange() {
        return defaultRange;
    }

    public int getPrecision() {
        return precision;
    }

    public String getSql(Dialect dialect, int range, int precision) {
        if (range == 0) {
            range = this.getDefaultRange();
        }
        if (precision == 0) {
            precision = this.getPrecision();
        }
        String sql = null;
        switch (dialect) {
            case POSTGRESQL:
                switch (this) {
                    case Text:
                    case TextArea:
                    case Attachment:
                        sql = " character varying(" + range + ") ";
                        break;
                    case Integer:
                        sql = " bigint ";
                        break;
                    case Decimal:
                        sql = " numeric(" + range + "," + precision + ") ";
                        break;
                    case Boolean:
                        sql = " boolean ";
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
                    default:
                        sql = " character varying(200) ";
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
