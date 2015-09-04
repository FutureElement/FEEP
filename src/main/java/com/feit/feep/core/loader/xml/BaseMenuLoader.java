package com.feit.feep.core.loader.xml;

import com.feit.feep.core.Global;
import com.feit.feep.core.loader.DefaultXMLLoader;
import com.feit.feep.core.loader.IFeepBaseMenuLoader;
import com.feit.feep.exception.xml.XmlException;
import com.feit.feep.mvc.entity.Menu;
import com.feit.feep.util.FeepUtil;

import org.dom4j.Attribute;
import org.dom4j.Element;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BaseMenuLoader extends DefaultXMLLoader implements IFeepBaseMenuLoader {

    private List<Menu> menus;

    public BaseMenuLoader(String fileName) throws XmlException {
        super(fileName);
    }

    public BaseMenuLoader(File file) throws XmlException {
        super(file);
    }

    public BaseMenuLoader(InputStream in) throws XmlException {
        super(in);
    }

    protected void parse() throws XmlException {
        Element root = xml.getRootElement();
        try {
            if (!"FEEP-MENU".equals(root.getName())) {
                throw new XmlException("BaseMenu.xml root node not exist : FEEP-MENU");
            }
            @SuppressWarnings("unchecked")
            List<Element> menuList = root.elements("menu");
            menus = new ArrayList<Menu>();
            for (Element element : menuList) {
                parseMenu(element, null);
            }
        } catch (Exception e) {
            Global.getInstance().logError(e);
            throw new XmlException(e);
        }
    }

    private void parseMenu(Element element, Menu parent) {
        Menu menu = new Menu();
        menu.setName(element.attributeValue("name"));
        menu.setDisplay(element.attributeValue("display"));
        Attribute shortcut = element.attribute("shortcut");
        if (null != shortcut && !FeepUtil.isNull(shortcut.getValue())) {
            menu.setShortcut(Boolean.valueOf(shortcut.getValue()));
        }
        if (element.hasContent()) {
            @SuppressWarnings("unchecked")
            List<Element> menuList = element.elements("menu");
            if (!FeepUtil.isNull(menuList)) {
                for (Element child : menuList) {
                    parseMenu(child, menu);
                }
            }
        }
        if (parent == null) {
            menus.add(menu);
        } else {
            if (null == parent.getChildren()) {
                parent.setChildren(new ArrayList<Menu>());
            }
            parent.getChildren().add(menu);
        }
    }

    @Override
    public List<Menu> getBaseMenus() {
        return menus;
    }
}