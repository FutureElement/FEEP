package com.feit.feep.core.loader.xml;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.feit.feep.util.FeepUtil;
import org.dom4j.Element;

import com.feit.feep.core.Global;
import com.feit.feep.core.loader.DefaultXMLLoader;
import com.feit.feep.core.loader.IFeepSqlMappingLoader;
import com.feit.feep.exception.xml.XmlException;

public class FeepSqlMappingLoader extends DefaultXMLLoader implements IFeepSqlMappingLoader {

    private static final String QNAME_ROOT = "FEEP-MAPPING";
    private static final String QNAME_SQL  = "SQL";
    private static final String QNAME_KEY  = "key";

    private Map<String, String> map;
    
    public FeepSqlMappingLoader(File file) throws XmlException {
        super(file);
    }

    public FeepSqlMappingLoader(String fileName) throws XmlException {
        super(fileName);
    }

    public FeepSqlMappingLoader(InputStream in) throws XmlException {
        super(in);
    }

    protected void parse() throws XmlException {
        Element root = xml.getRootElement();
        String qname = null;
        try{
            qname = root.getName();
            if(!QNAME_ROOT.equals(qname)){
                throw new XmlException("FeepSqlMappingLoader node " + qname + " not exist !");
            }
            qname = QNAME_SQL;
            @SuppressWarnings("unchecked")
            List<Element> sqls = root.elements(qname);
            if (!FeepUtil.isNull(sqls)) {
                this.map = new HashMap<String, String>();
                qname = QNAME_KEY;
                for (Element sql : sqls) {
                    this.map.put(sql.attributeValue(qname), sql.getTextTrim());
                }
            }
        }catch(Exception e){
            Global.getInstance().logError(e);
            throw new XmlException("FeepSqlMappingLoader Parse node " + qname + " error !", e);
        }
    }

    @Override
    public Map<String, String> getAllSqls() {
        return map;
    }

}