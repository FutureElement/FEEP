package com.feit.feep.dbms.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.entity.query.Page;
import com.feit.feep.exception.json.JsonException;
import com.feit.feep.util.FeepUtil;
import com.feit.feep.util.json.FeepJsonUtil;

public class EntityBeanSet {
    private List<EntityBean> list = null;
    private String moduleName;
    private Page page;
    private Map<String, Object>[] data;

    public EntityBeanSet() {
        list = new ArrayList<EntityBean>();
        page = new Page();
        page.setPageIndex(1);
        page.setPageSize(Global.getInstance().getFeepConfig().getDefaultPageSize());
        page.setTotalCount(0);
        page.setTotalPageNum(1);
    }

    public EntityBeanSet(List<EntityBean> entityBeans) {
        this();
        setResult(entityBeans);
    }

    public EntityBeanSet(EntityBean[] entityBeans) {
        this();
        setResult(entityBeans);
    }

    public void setResult(List<EntityBean> entityBeans) {
        if (!FeepUtil.isNull(entityBeans)) {
            list = entityBeans;
        }
    }

    public void setResult(EntityBean[] entityBeans) {
        if (!FeepUtil.isNull(entityBeans)) {
            for (EntityBean bean : entityBeans) {
                list.add(bean);
            }
        }
    }

    public void addResult(List<EntityBean> entityBeans) {
        list.addAll(entityBeans);
    }

    public void addResult(EntityBean entityBean) {
        list.add(entityBean);
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

    private void toData() {
        if (!FeepUtil.isNull(list)) {
            data = new HashMap[list.size()];
            for (int i = 0; i < list.size(); i++) {
                data[i] = list.get(i).getData();
            }
        }
    }

    public String toString() {
        if (null != list) {
            try {
                toData();
                return FeepJsonUtil.toJson(this);
            } catch (JsonException e) {
                Global.getInstance().logError("EntityBeanSet toString error", e);
                return list.toString();
            }
        } else {
            return null;
        }
    }

    public String toString(String dataFormat) {
        if (null != list) {
            try {
                toData();
                return FeepJsonUtil.toJson(this, dataFormat);
            } catch (JsonException e) {
                Global.getInstance().logError("EntityBeanSet toString with DataFormat error ,dataFormat:" + dataFormat, e);
                return toString();
            }
        } else {
            return null;
        }
    }

    public List<EntityBean> getList() {
        return list;
    }

}