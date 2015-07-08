package com.feit.feep.core.loader.entity;

import com.alibaba.druid.pool.DruidDataSource;
import com.feit.feep.core.Global;
import com.feit.feep.dbms.entity.datasource.DBInfo;
import com.feit.feep.dbms.util.MultiDataSource;
import com.feit.feep.mvc.fileupload.UploadConfig;
import org.springframework.beans.factory.DisposableBean;

public class FeepConfig implements DisposableBean {
    private String title;
    private String contextPath;
    private boolean devMode;
    private String tempPath;
    private int defaultPageSize;
    private DBInfo dbInfo;
    private UploadConfig uploadConfig;
    private boolean addUserToCache;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public boolean isDevMode() {
        return devMode;
    }

    public void setDevMode(boolean devMode) {
        this.devMode = devMode;
    }

    public String getTempPath() {
        return tempPath;
    }

    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }

    public int getDefaultPageSize() {
        return defaultPageSize;
    }

    public void setDefaultPageSize(int defaultPageSize) {
        this.defaultPageSize = defaultPageSize;
    }

    public DBInfo getDBInfo() {
        return dbInfo;
    }

    public void setDBInfo(DBInfo dbInfo) {
        this.dbInfo = dbInfo;
    }

    public UploadConfig getUploadConfig() {
        return uploadConfig;
    }

    public void setUploadConfig(UploadConfig uploadConfig) {
        this.uploadConfig = uploadConfig;
    }

    public boolean isAddUserToCache() {
        return addUserToCache;
    }

    public void setAddUserToCache(boolean addUserToCache) {
        this.addUserToCache = addUserToCache;
    }

    @Override
    public void destroy() throws Exception {
        try {
            DruidDataSource druidDataSource = (DruidDataSource) Global.getInstance().getDataSource(MultiDataSource.DEFAULT);
            druidDataSource.close();
        } catch (Exception e) {
            Global.getInstance().logError(e);
        }
    }
}
