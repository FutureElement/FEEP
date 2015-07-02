package com.feit.feep.dbms.entity.datasource;

import com.feit.feep.dbms.entity.module.FeepDataSource;

public class DBInfo {
    private String ip;
    private String port;
    private String username;
    private String password;
    private String dbname;
    private Dialect dialect;
    private int initSize;
    private int maxActive;

    public DBInfo() {
    }

    public DBInfo(String ip, String port, String username, String password, String dbname, Dialect dialect, int initSize, int maxActive) {
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;
        this.dbname = dbname;
        this.dialect = dialect;
        this.initSize = initSize;
        this.maxActive = maxActive;
    }

    public DBInfo(FeepDataSource feepDataSource) {
        this.ip = feepDataSource.getIp();
        this.port = feepDataSource.getPort();
        this.username = feepDataSource.getUsername();
        this.password = feepDataSource.getPassword();
        this.dbname = feepDataSource.getDbname();
        this.dialect = Dialect.get(feepDataSource.getDialect());
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public Dialect getDialect() {
        return dialect;
    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }

    public int getInitSize() {
        return initSize;
    }

    public void setInitSize(int initSize) {
        this.initSize = initSize;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public String getDriverClassName() {
        return dialect.getClassName();
    }

    public String getUrl() {
        return dialect.getUrl(ip, port, dbname);
    }

}
