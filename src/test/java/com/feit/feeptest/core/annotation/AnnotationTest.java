package com.feit.feeptest.core.annotation;

import com.feit.feep.core.Global;
import com.feit.feep.exception.ioc.IocException;
import org.junit.Test;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.core.loader.pool.FeepControllerIocPool;

public class AnnotationTest extends FeepJUnit {

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
}
