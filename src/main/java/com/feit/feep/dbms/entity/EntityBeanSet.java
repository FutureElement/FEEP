package com.feit.feep.dbms.entity;

import java.util.ArrayList;
import java.util.List;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.entity.query.Page;
import com.feit.feep.exception.json.JsonException;
import com.feit.feep.util.json.FeepJsonUtil;

public class EntityBeanSet {
    private List<EntityBean> list = null;
    private String           moduleName;
    private Page             page;

    public EntityBeanSet() {
        list = new ArrayList<EntityBean>();
        page = new Page();
        page.setPageIndex(1);
        page.setPageSize(Global.getInstance().getFeepConfig().getDefaultPageSize());
        page.setTotalCount(0);
        page.setTotalPageNum(1);
    }

    public EntityBeanSet(EntityBean[] entityBeans) {
        list = new ArrayList<EntityBean>();
        if (null != entityBeans) {
            for (EntityBean bean : entityBeans) {
                list.add(bean);
            }
        }
    }

    public int getSize() {
        return list.size();
    }

    public EntityBean get(int index) {
        return list.get(index);
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public void add(EntityBean bean) {
        list.add(bean);
    }

    public EntityBean[] toArray() {
        if (null != list) {
            return list.toArray(new EntityBean[list.size()]);
        } else {
            return new EntityBean[0];
        }
    }

    public String toString() {
        if (null != list) {
            try {
                return FeepJsonUtil.toJson(this);
            } catch (JsonException e) {
                Global.getInstance().logError("EntityBeanSet toString error",e);
                return list.toString();
            }
        } else {
            return null;
        }
    }

    public String toString(String dataFormat) {
        if (null != list) {
            try {
                return FeepJsonUtil.toJson(this, dataFormat);
            } catch (JsonException e) {
                Global.getInstance().logError("EntityBeanSet toString with DataFormat error ,dataFormat:" + dataFormat,e);
                return toString();
            }
        } else {
            return null;
        }
    }
}