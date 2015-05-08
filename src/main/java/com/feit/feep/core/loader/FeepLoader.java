package com.feit.feep.core.loader;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;

import com.feit.feep.core.Global;
import com.feit.feep.core.loader.entity.FeepConfig;
import com.feit.feep.core.loader.xml.FeepConfigLoader;
import com.feit.feep.core.loader.xml.FeepScannerLoader;
import com.feit.feep.exception.xml.XmlException;
import com.feit.feep.util.FeepUtil;
import com.feit.feep.util.resources.XmlMappingScanner;

public class FeepLoader {

    private FeepLoader() {

    }

    public static String[] getAnnotationScannerPackages() {
        try {
            String[] xmlPaths = XmlMappingScanner.getAllMappingFilePath(Global.PROJECT_SCANNER_FILEPATH);
            List<String> list = new LinkedList<String>();
            if (null != xmlPaths) {
                for (String filePath : xmlPaths) {
                    FeepScannerLoader scannerLoader = new FeepScannerLoader(filePath);
                    String[] packages = scannerLoader.getScannerPath();
                    if (null != packages) {
                        for (String packagePath : packages) {
                            list.add(packagePath);
                        }
                    }
                }
            }
            return list.toArray(new String[list.size()]);
        } catch (XmlException e) {
            throw new RuntimeException("FeepLoader getAnnotationScannerPackages error", e);
        }
    }

    public static FeepConfig getFeepConfig(ServletContext sc) throws XmlException {
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
