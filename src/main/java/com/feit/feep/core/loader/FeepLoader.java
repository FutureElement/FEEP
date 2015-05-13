package com.feit.feep.core.loader;

import java.io.InputStream;

import javax.servlet.ServletContext;

import com.feit.feep.core.Global;
import com.feit.feep.core.loader.entity.FeepConfig;
import com.feit.feep.core.loader.xml.FeepConfigLoader;
import com.feit.feep.exception.xml.XmlException;
import com.feit.feep.util.FeepUtil;

public class FeepLoader {

    private FeepLoader() {

    }

    public static FeepConfig getFeepConfig2(ServletContext sc) throws XmlException {
        InputStream in = sc.getResourceAsStream(Global.PROJECT_APPCONFIG_FILEPATH);
        FeepConfigLoader configLoader = new FeepConfigLoader(in);
        FeepConfig config = new FeepConfig();
        config.setAddUserToCache(configLoader.isAddUserToCache());
        config.setContextPath(configLoader.getContextPath());
        config.setDBInfo(configLoader.getDBInfo());
        config.setDefaultPageSize(configLoader.getDefaultPageSize());
        config.setDevMode(configLoader.isDevMode());
        config.setTempPath(configLoader.getTempPath());
        config.setUploadConfig(configLoader.getUploadConfig());
        FeepUtil.closeInputStream(in);
        return config;
    }

}
