package com.feit.feep.config.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.feit.feep.cache.FeepCacheManager;
import com.feit.feep.cache.ehcache.CacheFactory;
import com.feit.feep.cache.ehcache.CachePool;
import com.feit.feep.cache.ehcache.EhcacheManager;
import com.feit.feep.core.Global;
import com.feit.feep.core.loader.FeepLoader;
import com.feit.feep.core.loader.entity.FeepConfig;
import com.feit.feep.exception.FException;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.feit")
public class ApplicationConfig {

    @Autowired
    private ApplicationContext    ctx;

    @Autowired
    private WebApplicationContext webctx;

    @Bean(name = "feepConfig")
    public FeepConfig feepConfig() throws FException {
        Global.getInstance().setApplicationContext(ctx);
        if (null == Global.getInstance().getFeepConfig()) {
            Global.getInstance().setFeepConfgi(FeepLoader.getFeepConfig(webctx.getServletContext()));
        }
        return Global.getInstance().getFeepConfig();
    }

    @Bean(name = "cacheManager")
    public FeepCacheManager cacheManager() throws FException {
        EhcacheManager cacheManager = new EhcacheManager(Global.CACHE_CONFIG_PATH);
        /* 创建用户缓存 */
        cacheManager.addCache(CacheFactory.create(CachePool.USERCACHE.getCacheName(), new String[]{"username"}));
        return cacheManager;
    }

}
