package com.feit.feep.config.junit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.feit.feep.config.spring.ApplicationConfig;
import com.feit.feep.core.Global;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(classes = {ApplicationConfig.class})
public class FeepJUnit {

    @Autowired
    private ApplicationContext ctx;

    @BeforeClass
    public static void setUpBeforeClass() {
        Global.getInstance().setTestJunit(true);
    }

    @Before
    public void init() {
        Global.getInstance().logInfo("FeepJUnit init ...");
    }

    @Test
    public void index() {
        Global.getInstance().logInfo("FeepJUnit start ...");
    }
}