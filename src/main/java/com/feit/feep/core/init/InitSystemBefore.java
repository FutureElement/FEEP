package com.feit.feep.core.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import com.feit.feep.core.Global;
import com.feit.feep.core.loader.FeepLoader;
import com.feit.feep.exception.xml.XmlException;

public class InitSystemBefore {

    public void load(ServletContext sc) throws ServletException {
        Global.getInstance().logInfo("FEEP Loading Start ...", this.getClass());
        /* 解压包 */
        // TODO
        try {
            /* 加载 FeepConfig */
            Global.getInstance().setFeepConfgi(FeepLoader.getFeepConfig(sc));
        } catch (XmlException e) {
            throw new RuntimeException(e);
        }
    }

}
