package com.feit.feep.core.loader.xml;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.dom4j.Element;

import com.feit.feep.core.Global;
import com.feit.feep.core.loader.DefaultXMLLoader;
import com.feit.feep.core.loader.IFeepScannerLoader;
import com.feit.feep.exception.xml.XmlException;

public class FeepScannerLoader extends DefaultXMLLoader implements IFeepScannerLoader {

    private static final String QNAME_ROOT    = "FEEP-SCANNER";
    private static final String QNAME_ELEMENT = "Annotation";
    private static final String QNAME_LIST    = "package";

    private String[]            packages;

    public FeepScannerLoader(String fileName) throws XmlException {
        super(fileName);
    }

    public FeepScannerLoader(File file) throws XmlException {
        super(file);
    }

    public FeepScannerLoader(InputStream in) throws XmlException {
        super(in);
    }

    protected void parse() throws XmlException {
        Element root = xml.getRootElement();
        String qname = null;
        try {
            qname = root.getName();
            if (!QNAME_ROOT.equals(qname)) {
                throw new XmlException("FeepScannerLoader node " + qname + " not exist !");
            }
            qname = QNAME_ELEMENT;
            Element annotationScanner = root.element(qname);
            if (null == annotationScanner) {
                throw new XmlException("FeepScannerLoader node " + qname + " not exist !");
            }
            qname = QNAME_LIST;
            @SuppressWarnings("unchecked")
            List<Element> packageElements = annotationScanner.elements(qname);
            if (null != packageElements && !packageElements.isEmpty()) {
                this.packages = new String[packageElements.size()];
                for (int i = 0; i < packageElements.size(); i++) {
                    this.packages[i] = packageElements.get(i).getText();
                }
            }
        }catch (Exception e) {
            Global.getInstance().logError(e);
            throw new XmlException("FeepScannerLoader Parse node " + qname + " error !", e);
        }
    }

    @Override
    public String[] getScannerPath() {
        return packages;
    }

}