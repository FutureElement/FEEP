package com.feit.feep.core.loader.pool;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.feit.feep.core.loader.entity.IocObject;
import com.feit.feep.exception.ioc.IocException;

public class DefaultIocPool<T> implements IocPool<T> {

    protected Map<String, IocObject<T>> pool;

    public DefaultIocPool() {
        pool = new HashMap<String, IocObject<T>>();
    }

    public void set(String name, T t, Class<?> classType) throws IocException {
        if (null == pool.get(name)) {
            IocObject<T> iocObj = new IocObject<T>();
            iocObj.setName(name);
            iocObj.setValue(t);
            iocObj.setType(classType);
            pool.put(name, iocObj);
        } else {
            throw new IocException("name of is exist,name: " + name);
        }
    }

    public IocObject<T> get(String name) throws IocException {
        return pool.get(name);
    }

    public boolean isExist(String name) throws IocException {
        return null != pool.get(name);
    }

    public String[] getNames() throws IocException {
        return pool.keySet().toArray(new String[pool.size()]);
    }

    public void clear() throws IocException {
        if (null != pool){
            pool.clear();
        }
    }

    public List<IocObject<T>> getAll() throws IocException {
        List<IocObject<T>> list = new LinkedList<IocObject<T>>();
        String[] names = getNames();
        if (null != names) {
            for (String name : names) {
                list.add(pool.get(name));
            }
        }
        return list;
    }
}