package com.feit.feep.dbms.entity.module;

import com.feit.feep.dbms.entity.EntityBean;

import java.io.Serializable;

/**
 * 数据源
 * Created by ZhangGang on 2015/6/8 0008.
 */
public class FeepDataSource implements Serializable {

    private static final long serialVersionUID = 6410855361915307393L;

    public static final String TABLE_NAME = "feep_datasource";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_TYPE = "type";
    public static final String[] searchableFieldName = {FIELD_NAME, FIELD_TYPE};


    private String id;
    /*数据源名称*/
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
    /*数据库名称*/
    private String dbname;
    /*排序*/
    private int sort;
    /*类型*/
    private int type;

    public FeepDataSource() {
    }

    public FeepDataSource(String id, String name, String showname, int dialect, String ip, String port, String username, String password, String dbname, int sort, int type) {
        this.id = id;
        this.name = name;
        this.showname = showname;
        this.dialect = dialect;
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;
        this.dbname = dbname;
        this.sort = sort;
        this.type = type;
    }

    public FeepDataSource(EntityBean bean) {
        this.id = bean.getString("id");
        this.name = bean.getString("name");
        this.showname = bean.getString("showname");
        this.dialect = bean.getInt("dialect");
        this.ip = bean.getString("ip");
        this.port = bean.getString("port");
        this.username = bean.getString("username");
        this.password = bean.getString("password");
        this.dbname = bean.getString("dbname");
        this.sort = bean.getInt("sort");
        this.type = bean.getInt("type");

    }

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

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
