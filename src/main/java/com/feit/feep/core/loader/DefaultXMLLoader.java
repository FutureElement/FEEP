package com.feit.feep.core.loader;

import java.io.File;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.feit.feep.exception.xml.XmlException;

public class DefaultXMLLoader {

    protected Document xml;

    protected DefaultXMLLoader(String fileName) throws XmlException {
        this(new File(fileName));
    }

    protected DefaultXMLLoader(File file) throws XmlException {
        this.xml = read(file);
        parse();
    }

    protected DefaultXMLLoader(InputStream in) throws XmlException {
        this.xml = read(in);
        parse();
    }

    protected void parse() throws XmlException {
        //method need to reaload
    }

    protected Document read(File file) throws XmlException {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            throw new XmlException(e);
        }
        return document;
    }

    protected Document read(InputStream in) throws XmlException {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(in);
        } catch (DocumentException e) {
            throw new XmlException(e);
        }
        return document;
    }
}