package com.feit.feep.dbms.init;

import com.feit.feep.cache.ehcache.CachePool;
import com.feit.feep.core.Global;
import com.feit.feep.dbms.dao.IFeepTableDao;
import com.feit.feep.dbms.dao.IFeepTableFieldDao;
import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.dbms.service.ITableManagementService;
import com.feit.feep.util.FeepUtil;
import org.springframework.context.ApplicationContext;

import java.util.LinkedList;
import java.util.List;

public class InitCacheData {

    private ApplicationContext ctx;

    private IFeepTableDao feepTableDao;

    private IFeepTableFieldDao feepTableFieldDao;

    public InitCacheData(ApplicationContext ctx) {
        this.ctx = ctx;
        feepTableDao = ctx.getBean(IFeepTableDao.class);
        feepTableFieldDao = ctx.getBean(IFeepTableFieldDao.class);
    }

    public void init() throws Exception {
        //加载table到缓存
        List<FeepTable> feepTables = feepTableDao.queryFeepTable(new FeepQueryBean());
        if (!FeepUtil.isNull(feepTables)) {
            for (FeepTable table : feepTables) {
                Global.getInstance().getCacheManager().put(CachePool.TABLECACHE, table.getId(), table);
            }
        }
        //加载tablefield到缓存
        List<FeepTableField> feepTableFields = feepTableFieldDao.findAllFields();
        if (!FeepUtil.isNull(feepTableFields)) {
            for (FeepTableField field : feepTableFields) {
                Global.getInstance().getCacheManager().put(CachePool.TABLEFIELDCACHE, field.getId(), field);
            }
        }
    }

}
