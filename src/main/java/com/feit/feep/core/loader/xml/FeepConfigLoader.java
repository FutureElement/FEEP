package com.feit.feep.core.loader.xml;

import java.io.File;
import java.io.InputStream;

import org.dom4j.Element;

import com.feit.feep.core.Global;
import com.feit.feep.core.loader.DefaultXMLLoader;
import com.feit.feep.core.loader.IFeepConfigLoader;
import com.feit.feep.dbms.entity.datasource.DBInfo;
import com.feit.feep.dbms.entity.datasource.Dialect;
import com.feit.feep.exception.xml.XmlException;
import com.feit.feep.mvc.fileupload.UploadConfig;

public class FeepConfigLoader extends DefaultXMLLoader implements IFeepConfigLoader {

    private String       title;
    private String       contextPath;
    private boolean     devMode;
    private String       tempPath;
    private int           defaultPageSize;
    private DBInfo        dbInfo;
    private UploadConfig  uploadConfig;
    private boolean      addUserToCache;

    public FeepConfigLoader(String fileName) throws XmlException {
        super(fileName);
    }

    public FeepConfigLoader(File file) throws XmlException {
        super(file);
    }

    public FeepConfigLoader(InputStream in) throws XmlException {
        super(in);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public DBInfo getDBInfo() {
        return dbInfo;
    }

    @Override
    public boolean isDevMode() {
        return devMode;
    }

    @Override
    public String getTempPath() {
        return tempPath;
    }

    @Override
    public UploadConfig getUploadConfig() {
        return uploadConfig;
    }

    protected void parse() throws XmlException {
        Element root = xml.getRootElement();
        String qname = null;
        try {
            qname = root.getName();
            if (!"FEEP".equals(qname)) {
                throw new XmlException("FEEP.xml root node not exist : FEEP");
            }
            qname = "Constant";
            Element constant = root.element(qname);
            if (null == constant) {
                throw new XmlException("FEEP.xml node not exist : " + qname);
            }
            qname = "Title";
            this.title = constant.element(qname).getTextTrim();
            qname = "ContextPath";
            this.contextPath = constant.element(qname).getTextTrim();
            qname = "DevMode";
            this.devMode = Boolean.valueOf(constant.element(qname).getTextTrim());
            qname = "Temp";
            this.tempPath = constant.element(qname).getTextTrim();
            qname = "DefaultPageSize";
            this.defaultPageSize = Integer.parseInt(constant.element(qname).getTextTrim());
            qname = "AddUserToCache";
            this.addUserToCache = Boolean.parseBoolean(constant.element(qname).getTextTrim());
            qname = "FileUpload";
            Element fileUpload = root.element(qname);
            this.uploadConfig = new UploadConfig();
            this.uploadConfig.setUploadPath(fileUpload.attributeValue("uploadPath"));
            this.uploadConfig.setMaxUploadSize(Long.parseLong(fileUpload.attributeValue("maxUploadSize")));
            this.uploadConfig.setMaxInMemorySize(Integer.parseInt(fileUpload.attributeValue("maxInMemorySize")));
            qname = "DataSource";
            Element dataSource = root.element(qname);
            this.dbInfo = new DBInfo();
            this.dbInfo.setIp(dataSource.attributeValue("ip"));
            this.dbInfo.setPort(dataSource.attributeValue("port"));
            this.dbInfo.setDbname(dataSource.attributeValue("dbname"));
            this.dbInfo.setDialect(Dialect.valueOf(dataSource.attributeValue("dialect").toUpperCase()));
            this.dbInfo.setUsername(dataSource.attributeValue("username"));
            this.dbInfo.setPassword(dataSource.attributeValue("password"));
            this.dbInfo.setMaxActive(Integer.parseInt(dataSource.attributeValue("maxActive")));
            this.dbInfo.setInitSize(Integer.parseInt(dataSource.attributeValue("initSize")));
        }catch (Exception e) {
            Global.getInstance().logError(e);
            throw new XmlException("Parse FEEP.xml node " + qname + " error !", e);
        }
    }

    @Override
    public String getContextPath() {
        return this.contextPath;
    }

    @Override
    public int getDefaultPageSize() {
        return this.defaultPageSize;
    }

    @Override
    public boolean isAddUserToCache() {
        return this.addUserToCache;
    }

}