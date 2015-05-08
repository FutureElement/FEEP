package com.feit.feep.core.loader.entity;

import com.feit.feep.core.Global;

public class IocObject<T> implements Cloneable {
    private String   name;
    private T        value;
    private Class<?> type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    public IocObject<T> clone() {
        try {
            return (IocObject<T>) super.clone();
        }catch (CloneNotSupportedException e) {
            Global.getInstance().logError("IocObject clone error , classType:"+ value,e);
            return null;
        }
    }
}