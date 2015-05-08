package com.feit.feep.core.loader;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.feit.feep.core.Global;
import com.feit.feep.exception.ioc.IocException;
import com.feit.feep.util.FeepUtil;
import com.feit.feep.util.resources.PackageScanner;

public class DefaultIocLoader {

    private List<Class<? extends Annotation>>                annotationType = null;

    private Map<Class<? extends Annotation>, List<Class<?>>> customAnnotationMap;

    public DefaultIocLoader() throws IocException {
        customAnnotationMap = new HashMap<Class<? extends Annotation>, List<Class<?>>>();
    }

    public void load(String[] packages) throws IocException {
        for (String path : packages) {
            scanner(path);
        }
    }

    private void scanner(String path) throws IocException {
        if (FeepUtil.isNull(path)){
            return;
        }
        String[] classNames = PackageScanner.getClassNameByPackage(path);
        if (null != classNames) {
            for (String className : classNames) {
                if (FeepUtil.isNull(className)) {
                    continue;
                }
                Class<?> cls;
                try {
                    cls = Class.forName(className);
                    if (null != cls && null != annotationType) {
                        for (Class<? extends Annotation> type : annotationType) {
                            if (cls.isAnnotationPresent(type)) {
                                add(cls, type);
                                break;
                            }
                        }
                    }
                } catch (ClassNotFoundException eo) {
                    Global.getInstance().logError("AnnotationLoader load class error", eo);
                }
            }
        }
    }

    private void add(Class<?> cls, Class<? extends Annotation> type) throws IocException {
        List<Class<?>> pool = customAnnotationMap.get(type);
        if (null == pool) {
            pool = new LinkedList<Class<?>>();
        }
        pool.add(cls);
        customAnnotationMap.put(type, pool);
    }

    public List<Class<?>> getCustomAnnotationList(Class<? extends Annotation> type) {
        return customAnnotationMap.get(type);
    }

    public void setCustomAnnotationType(List<Class<? extends Annotation>> annotationType) {
        this.annotationType = annotationType;
    }
}