package com.feit.feep.dbms.init;

import com.feit.feep.cache.ehcache.CachePool;
import com.feit.feep.core.Global;
import com.feit.feep.dbms.dao.*;
import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.dictionary.Dictionary;
import com.feit.feep.dbms.entity.dictionary.DictionaryItem;
import com.feit.feep.dbms.entity.module.FeepDataSource;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.dbms.service.IDataSourceService;
import com.feit.feep.dbms.service.ITableManagementService;
import com.feit.feep.dbms.util.DataSourceUtil;
import com.feit.feep.dbms.util.MultiDataSource;
import com.feit.feep.util.FeepUtil;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

public class InitCacheData {

    private ApplicationContext ctx;

    private IFeepTableDao feepTableDao;

    private IFeepTableFieldDao feepTableFieldDao;

    private IFeepDataSourceDao dataSourceDao;

    private IFeepDictionaryDao dictionaryDao;

    private IFeepDictionaryItemDao dictionaryItemDao;

    public InitCacheData(ApplicationContext ctx) {
        this.ctx = ctx;
        feepTableDao = ctx.getBean(IFeepTableDao.class);
        feepTableFieldDao = ctx.getBean(IFeepTableFieldDao.class);
        dataSourceDao = ctx.getBean(IFeepDataSourceDao.class);
        dictionaryDao = ctx.getBean(IFeepDictionaryDao.class);
        dictionaryItemDao = ctx.getBean(IFeepDictionaryItemDao.class);
    }

    public void init() throws Exception {
        //加载数据源到缓存
        MultiDataSource multiDataSource = MultiDataSource.getInstance();
        List<FeepDataSource> feepDataSourceList = dataSourceDao.findAll();
        if (!FeepUtil.isNull(feepDataSourceList)) {
            for (FeepDataSource feepDataSource : feepDataSourceList) {
                String key = feepDataSource.getName();
                DataSource dataSource = DataSourceUtil.getDataSource(feepDataSource);
                multiDataSource.addDataSource(key, dataSource);
                multiDataSource.addJdbcTemplate(key, DataSourceUtil.getJdbcTemplate(dataSource));
                multiDataSource.addTransactionTemplate(key, DataSourceUtil.getTransactionTemplate(dataSource));
            }
        }
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
        //加载dictionary到缓存
        List<Dictionary> dictionaryList = dictionaryDao.queryDictionary(new FeepQueryBean());
        if (!FeepUtil.isNull(dictionaryList)) {
            for (Dictionary dictionary : dictionaryList) {
                Global.getInstance().getCacheManager().put(CachePool.DICTIONARYCACHE, dictionary.getId(), dictionary);
            }
        }
        //加载dictionaryItems到缓存
        List<DictionaryItem> dictionaryItems = dictionaryItemDao.findAllItems();
        if (!FeepUtil.isNull(dictionaryItems)) {
            for (DictionaryItem dictionaryItem : dictionaryItems) {
                Global.getInstance().getCacheManager().put(CachePool.DICTIONARYITEMCACHE, dictionaryItem.getId(), dictionaryItem);
            }
        }
    }


}
