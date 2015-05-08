package com.feit.feeptest.core.loader;

import org.junit.Test;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.core.Global;

public class TestMessageLoader extends FeepJUnit {
    @Test
    public void test1() {
        String a = Global.getInstance().getMessageResource().getMessage("common.system.name");
        String b = Global.getInstance().getMessageResource().getMessage("common.system.version", new Object[]{"1.0"});
        Global.getInstance().logInfo(a);
        Global.getInstance().logInfo(b);
    }
}
