package com.feit.feep.config.spring;

import java.io.InputStream;

import com.feit.feep.dbms.entity.dictionary.Dictionary;
import com.feit.feep.dbms.entity.dictionary.DictionaryItem;
import com.feit.feep.dbms.entity.module.*;
import com.feit.feep.system.entity.FeepUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.feit.feep.cache.FeepCacheManager;
import com.feit.feep.cache.ehcache.CacheFactory;
import com.feit.feep.cache.ehcache.CachePool;
import com.feit.feep.cache.ehcache.EhcacheManager;
import com.feit.feep.core.Global;
import com.feit.feep.core.loader.entity.FeepConfig;
import com.feit.feep.core.loader.xml.FeepConfigLoader;
import com.feit.feep.exception.FException;
import com.feit.feep.util.FeepUtil;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.feit")
@TransactionConfiguration(transactionManager = "transactionManager")
public class ApplicationConfig {

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private WebApplicationContext webctx;

    @Bean(name = "feepConfig")
    public FeepConfig feepConfig() throws FException {
        Global.getInstance().setApplicationContext(ctx);
        InputStream in = webctx.getServletContext().getResourceAsStream(Global.PROJECT_APPCONFIG_FILEPATH);
        FeepConfigLoader configLoader = new FeepConfigLoader(in);
        FeepConfig config = new FeepConfig();
        config.setTitle(configLoader.getTitle());
        config.setAddUserToCache(configLoader.isAddUserToCache());
        config.setContextPath(configLoader.getContextPath());
        config.setDBInfo(configLoader.getDBInfo());
        config.setDefaultPageSize(configLoader.getDefaultPageSize());
        config.setDevMode(configLoader.isDevMode());
        config.setTempPath(configLoader.getTempPath());
        config.setUploadConfig(configLoader.getUploadConfig());
        FeepUtil.closeInputStream(in);
        return config;
    }

    @Bean(name = "cacheManager")
    public FeepCacheManager cacheManager() throws FException {
        EhcacheManager cacheManager = new EhcacheManager(Global.CACHE_CONFIG_PATH);
        /* 创建用户缓存池 */
        cacheManager.addCache(CacheFactory.create(CachePool.USERCACHE.getCacheName(), FeepUser.column));
        /* 创建数据源缓存池 */
        cacheManager.addCache(CacheFactory.create(CachePool.FEEPDATASOURCE.getCacheName(), FeepDataSource.searchableFieldName));
        /* 创建数据表缓存池 */
        cacheManager.addCache(CacheFactory.create(CachePool.TABLECACHE.getCacheName(), FeepTable.column));
        /* 创建数据表字段缓存池 */
        cacheManager.addCache(CacheFactory.create(CachePool.TABLEFIELDCACHE.getCacheName(), new String[]{FeepTableField.pk}));
        /* 创建数据字典缓存池 */
        cacheManager.addCache(CacheFactory.create(CachePool.DICTIONARYCACHE.getCacheName(), Dictionary.column));
        /* 创建数据字典项缓存池 */
        cacheManager.addCache(CacheFactory.create(CachePool.DICTIONARYITEMCACHE.getCacheName(), new String[]{DictionaryItem.pk}));
        /* 创建数据模型缓存池 */
        cacheManager.addCache(CacheFactory.create(CachePool.MODULECACHE.getCacheName(), FeepModule.column));
        /* 创建数据模型字段缓存池 */
        cacheManager.addCache(CacheFactory.create(CachePool.MODULEFIELDCACHE.getCacheName(), new String[]{FeepModuleField.pk}));
        /* 创建数据模型表关联缓存池 */
        cacheManager.addCache(CacheFactory.create(CachePool.TABLEMODULERELATIONCACHE.getCacheName(), new String[]{FeepTableModuleRelation.pk}));
        /* 创建数据模型字段关联缓存池 */
        cacheManager.addCache(CacheFactory.create(CachePool.TABLEFIELDRELATIONCACHE.getCacheName(), new String[]{FeepTableFieldRelation.pk}));
        return cacheManager;
    }

}
