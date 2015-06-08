package com.feit.feep.dbms.entity.datasource;

public enum Dialect {

    ORACLE(0), POSTGRESQL(1), MYSQL(2), SQLSERVER(3);

    private static final String RE_IP = "#ip#";
    private static final String RE_PORT = "#port#";
    private static final String RE_DBNAME = "#dbname#";

    private String url;
    private String className;
    private int dbtype;

    Dialect(int dbtype) {
        this.dbtype = dbtype;
        switch (dbtype) {
            case 0:
                this.className = "oracle.jdbc.driver.OracleDriver";
                this.url = "jdbc:oracle:thin:@" + RE_IP + ":" + RE_PORT + ":" + RE_DBNAME;
                break;
            case 1:
                this.className = "org.postgresql.Driver";
                this.url = "jdbc:postgresql://" + RE_IP + ":" + RE_PORT + "/" + RE_DBNAME;
                break;
            case 2:
                this.className = "com.mysql.jdbc.Driver";
                this.url = "jdbc:mysql://" + RE_IP + ":" + RE_PORT + "/" + RE_DBNAME;
                break;
            case 3:
                this.className = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
                this.url = "jdbc:microsoft:sqlserver://" + RE_IP + ":" + RE_PORT + ";DatabaseName=" + RE_DBNAME;
                break;
            default:
                break;
        }

    }

    public int getDbtype() {
        return dbtype;
    }

    public String getClassName() {
        return className;
    }

    public String getUrl(String ip, String port, String dbname) {
        return url.replace(RE_IP, ip).replace(RE_PORT, port).replace(RE_DBNAME, dbname);
    }

    public Dialect get(int dbtype) {
        Dialect[] dialects = Dialect.values();
        for (Dialect dialect : dialects) {
            if (dialect.getDbtype() == dbtype) {
                return dialect;
            }
        }
        return null;
    }
}
