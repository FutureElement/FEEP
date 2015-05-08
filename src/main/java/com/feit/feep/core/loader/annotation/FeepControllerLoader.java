package com.feit.feep.core.loader.annotation;

import java.lang.reflect.Method;
import java.util.List;

import com.feit.feep.core.Global;
import com.feit.feep.core.loader.pool.FeepControllerIocPool;
import com.feit.feep.exception.ioc.IocException;

public class FeepControllerLoader implements IAnnotationLoader {

    /*private ApplicationContext ctx;

    public FeepControllerLoader() throws IocException {
        ctx = Global.getInstance().getApplicationContext();
    }*/

    private void addToPool(Class<?> cls) throws IocException {
        try {
            //ctx.getAutowireCapableBeanFactory().createBean(cls, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
            Method[] methods = cls.getMethods();
            if (null != methods) {
                for (Method m : methods) {
                    if (!m.getDeclaringClass().equals(cls)) {
                        continue;
                    }
                    FeepControllerIocPool.getInstance().set(m.getName(), m, cls);
                }
            }
        } catch (Exception e) {
            Global.getInstance().logError(e);
            throw new IocException(e);
        }
    }

    @Override
    public void load(List<Class<?>> list) throws IocException {
        if (null != list && !list.isEmpty()) {
            for (Class<?> cls : list) {
                addToPool(cls);
            }
        }
    }
}