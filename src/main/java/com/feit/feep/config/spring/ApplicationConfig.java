package com.feit.feep.config.spring;

import java.io.InputStream;

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
        /* 创建用户缓存 */
        cacheManager.addCache(CacheFactory.create(CachePool.USERCACHE.getCacheName(), new String[]{"username"}));
        return cacheManager;
    }

}
