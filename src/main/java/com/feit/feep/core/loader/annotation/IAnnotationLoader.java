package com.feit.feep.core.loader.annotation;

import java.util.List;

import com.feit.feep.exception.ioc.IocException;

public interface IAnnotationLoader {

    void load(List<Class<?>> list) throws IocException;

}
