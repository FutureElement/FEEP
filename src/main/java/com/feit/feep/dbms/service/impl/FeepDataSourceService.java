package com.feit.feep.dbms.service.impl;

import com.feit.feep.cache.ehcache.CachePool;
import com.feit.feep.core.Global;
import com.feit.feep.dbms.dao.IFeepDataSourceDao;
import com.feit.feep.dbms.entity.EntityBean;
import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.datasource.DBInfo;
import com.feit.feep.dbms.entity.datasource.DataSourceType;
import com.feit.feep.dbms.entity.module.FeepDataSource;
import com.feit.feep.dbms.service.IDataSourceService;
import com.feit.feep.util.FeepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


/**
 * 数据源service实现类
 * Created by zhanggang on 2015/7/2.
 */
@Service
public class FeepDataSourceService implements IDataSourceService {

    @Autowired
    private IFeepDataSourceDao dataSourceDao;

    @Override
    public String addDataSource(FeepDataSource feepDataSource) throws Exception {
        try {
            String id = dataSourceDao.addDataSource(feepDataSource);
            feepDataSource.setId(id);
            Global.getInstance().getCacheManager().put(CachePool.FEEPDATASOURCE, id, feepDataSource);
            return id;
        } catch (Exception e) {
            Global.getInstance().logInfo(e);
            return null;
        }
    }

    @Override
    public boolean deleteDataSource(String id) throws Exception {
        try {
            boolean isDel = dataSourceDao.deleteDataSource(id);
            if (isDel) {
                Global.getInstance().getCacheManager().remove(CachePool.FEEPDATASOURCE, id);
            }
            return isDel;
        } catch (Exception e) {
            Global.getInstance().logInfo(e);
            return false;
        }
    }

    @Override
    public boolean modifyDataSource(FeepDataSource feepDataSource) throws Exception {
        try {
            boolean isModify = dataSourceDao.updateDataSource(feepDataSource);
            if (isModify) {
                Global.getInstance().getCacheManager().update(CachePool.FEEPDATASOURCE, feepDataSource.getId(), feepDataSource);
            }
            return isModify;
        } catch (Exception e) {
            Global.getInstance().logInfo(e);
            return false;
        }
    }

    @Override
    public EntityBeanSet findAllDataSource() throws Exception {
        EntityBeanSet ebs = null;
        try {
            List<FeepDataSource> feepDataSourceList = Global.getInstance().getCacheManager().getAll(CachePool.FEEPDATASOURCE, FeepDataSource.class);
            if (!FeepUtil.isNull(feepDataSourceList)) {
                List<EntityBean> entityBeans = new LinkedList<EntityBean>();
                for (FeepDataSource feepDataSource : feepDataSourceList) {
                    EntityBean bean = new EntityBean();
                    bean.set("id", feepDataSource.getId());
                    bean.set("name", feepDataSource.getName());
                    bean.set("showname", feepDataSource.getShowname());
                    bean.set("dialect", feepDataSource.getDialect());
                    bean.set("ip", feepDataSource.getIp());
                    bean.set("port", feepDataSource.getPort());
                    bean.set("username", feepDataSource.getUsername());
                    bean.set("password", feepDataSource.getPassword());
                    bean.set("dbname", feepDataSource.getDbname());
                    bean.set("sort", feepDataSource.getSort());
                    bean.set("type", feepDataSource.getType());
                    entityBeans.add(bean);
                }
                ebs = new EntityBeanSet(entityBeans);
            }
        } catch (Exception e) {
            Global.getInstance().logInfo(e);
        }
        return ebs;
    }

    @Override
    public FeepDataSource findDataSourceById(String id) throws Exception {
        try {
            FeepDataSource feepDataSource = (FeepDataSource) Global.getInstance().getCacheManager().get(CachePool.FEEPDATASOURCE, id);
            if (feepDataSource.getType() == DataSourceType.DEFAULT.getType()) return getDefaultDataSourceInfo();
            else return feepDataSource;
        } catch (Exception e) {
            Global.getInstance().logInfo(e);
            return null;
        }
    }

    @Override
    public FeepDataSource findDataSourceByName(String name) throws Exception {
        try {
            List<FeepDataSource> feepDataSourceList = Global.getInstance().getCacheManager().findByAttribute(CachePool.FEEPDATASOURCE, FeepDataSource.FIELD_NAME, name, FeepDataSource.class);
            if (FeepUtil.isNull(feepDataSourceList)) return null;
            FeepDataSource feepDataSource = feepDataSourceList.get(0);
            if (feepDataSource.getType() == DataSourceType.DEFAULT.getType()) return getDefaultDataSourceInfo();
            else return feepDataSource;
        } catch (Exception e) {
            Global.getInstance().logInfo(e);
            return null;
        }
    }

    @Override
    public FeepDataSource getDefaultDataSourceInfo() throws Exception {
        try {
            FeepDataSource feepDataSource;
            List<FeepDataSource> feepDataSourceList = Global.getInstance().getCacheManager().findByAttribute(CachePool.FEEPDATASOURCE, FeepDataSource.FIELD_TYPE, DataSourceType.DEFAULT.getType(), FeepDataSource.class);
            if (FeepUtil.isNull(feepDataSourceList)) return null;
            else feepDataSource = feepDataSourceList.get(0);
            DBInfo dbInfo = Global.getInstance().getFeepConfig().getDBInfo();
            feepDataSource.setDialect(dbInfo.getDialect().getDbtype());
            feepDataSource.setIp(dbInfo.getIp());
            feepDataSource.setPort(dbInfo.getPort());
            feepDataSource.setUsername(dbInfo.getUsername());
            feepDataSource.setPassword(dbInfo.getPassword());
            feepDataSource.setDbname(dbInfo.getDbname());
            return feepDataSource;
        } catch (Exception e) {
            Global.getInstance().logInfo(e);
            return null;
        }
    }

}
