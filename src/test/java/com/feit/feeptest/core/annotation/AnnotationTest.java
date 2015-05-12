package com.feit.feeptest.core.annotation;

import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.core.Global;
import com.feit.feep.core.annotation.FeepController;
import com.feit.feep.core.loader.pool.FeepControllerIocPool;
import com.feit.feep.exception.ioc.IocException;

public class AnnotationTest extends FeepJUnit {

    @Autowired
    private ApplicationContext ctx;

    @Test
    public void test1() throws IocException {
        String[] names = FeepControllerIocPool.getInstance().getNames();
        if (null == names || names.length == 0) {
            throw new IocException("error");
        }
        for (String n : names) {
            Global.getInstance().logInfo(FeepControllerIocPool.getInstance().get(n).getName());
            Global.getInstance().logInfo(FeepControllerIocPool.getInstance().get(n).getType().getName());
        }
    }

    @Test
    public void test2() {
        Map<String, Object> map = ctx.getBeansWithAnnotation(FeepController.class);
        Set<String> keys = map.keySet();
        if (null != keys) {
            for (String key : keys) {
                Object o = map.get(key);
                Global.getInstance().logInfo(o.getClass().getName());
            }
        }
    }
}
