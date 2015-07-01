package com.feit.feep.dbms.entity.module;

import com.feit.feep.dbms.entity.datasource.Dialect;

import java.io.Serializable;

/**
 * 数据源
 * Created by ZhangGang on 2015/6/8 0008.
 */
public class FeepDataSource implements Serializable {

    private static final long serialVersionUID = 6410855361915307393L;

    private String id;
    /*数据库名称*/
    private String name;
    /*数据源显示名*/
    private String showname;
    /*数据库方言*/
    private int dialect;
    /*数据库ip*/
    private String ip;
    /*数据库端口*/
    private String port;
    /*用户名*/
    private String username;
    /*密码*/
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public int getDialect() {
        return dialect;
    }

    public void setDialect(int dialect) {
        this.dialect = dialect;
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
}
