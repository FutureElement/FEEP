package com.feit.feep.core.loader.pool;

import java.util.List;

import com.feit.feep.core.loader.entity.IocObject;
import com.feit.feep.exception.ioc.IocException;

public interface IocPool<T> {

    void set(String name, T t, Class<?> classType) throws IocException;

    IocObject<T> get(String name) throws IocException;

    boolean isExist(String name) throws IocException;

    String[] getNames() throws IocException;

    List<IocObject<T>> getAll() throws IocException;

    void clear() throws IocException;

}
