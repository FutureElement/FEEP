package com.feit.feep.core.loader;

import com.feit.feep.nosql.entity.NoSqlDBConfig;
import com.feit.feep.dbms.entity.datasource.DBInfo;
import com.feit.feep.mvc.fileupload.UploadConfig;

public interface IFeepConfigLoader {

    String getTitle();

    DBInfo getDBInfo();

    boolean isDevMode();

    String getTempPath();

    UploadConfig getUploadConfig();

    String getContextPath();

    int getDefaultPageSize();

    boolean isAddUserToCache();

    NoSqlDBConfig getNoSqlDBConfig();
}
