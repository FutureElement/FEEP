package com.feit.feep.core.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import com.feit.feep.core.Global;

public class InitSystemBefore {

    public void load(ServletContext sc) throws ServletException {
        Global.getInstance().logInfo("FEEP Loading Start ...", this.getClass());
        /* 解压包 */
        // TODO
    }

}
