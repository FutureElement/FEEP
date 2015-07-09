package com.feit.feep.nosql.entity;

/**
 * Created by zhanggang on 2015/7/9.
 */
public class NoSqlDBConfig {
    private String ip;
    private int port;
    private String dbName;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
