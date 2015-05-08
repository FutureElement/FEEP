package com.feit.feeptest.util.browser;

import org.junit.Test;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.util.browser.BrowserUtil;

public class TestBrowser extends FeepJUnit {

    @Test
    public void test1() {
        BrowserUtil.openBrowser("www.baidu.com");
    }
}
