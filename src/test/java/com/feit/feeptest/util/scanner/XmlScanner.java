package com.feit.feeptest.util.scanner;

import org.junit.Test;

import com.feit.feep.core.Global;
import com.feit.feep.util.resources.PackageScanner;
import com.feit.feep.util.resources.XmlMappingScanner;

public class XmlScanner {
    @Test
    public void a() {
        String[] a = XmlMappingScanner.getAllMappingFilePath(Global.SQL_CONFIG_PATH);
        for (String n : a) {
            Global.getInstance().logInfo(n);
        }
    }

    @Test
    public void b() {
        String[] classNames2 = PackageScanner.getClassNameByPackage("com.feit.feep.system");
        for (String c2 : classNames2) {
            Global.getInstance().logInfo(c2);
        }
    }

}
