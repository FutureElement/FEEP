package com.feit.feep.core.init;

import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.feit.feep.cache.ehcache.CachePool;
import com.feit.feep.core.Global;
import com.feit.feep.core.annotation.FeepController;
import com.feit.feep.core.loader.DefaultIocLoader;
import com.feit.feep.core.loader.annotation.FeepControllerLoader;
import com.feit.feep.core.loader.entity.FeepConfig;
import com.feit.feep.core.loader.xml.FeepSqlMappingLoader;
import com.feit.feep.exception.FException;
import com.feit.feep.exception.xml.XmlException;
import com.feit.feep.mvc.entity.FeepMvcKey;
import com.feit.feep.system.service.impl.UserService;
import com.feit.feep.util.browser.BrowserUtil;
import com.feit.feep.util.resources.XmlMappingScanner;

/**
 * 初始化系统，在spring加载完成后加载
 * 
 * @author ZhangGang
 *
 */
@Component("BeanDefineConfigue")
public class InitSystemAfter implements ApplicationListener<ContextRefreshedEvent> {

    private WebApplicationContext webAppContext;

    private FeepConfig            config;

    public void init() {
        Global.getInstance().logInfo("FEEP System init ...", this.getClass());
        try {
            config = Global.getInstance().getFeepConfig();
            /* 加载 FeepController */
            loadFeepController();
            /* 加载SQL到缓存 */
            loadSqltoCache();
            /* 加载用户到缓存 */
            loadUserToCache();
            /* 加载 Resource */
            loadResourceToCache();
            boolean isTest = Global.getInstance().isTestJunit();
            if (!isTest) {
                BrowserUtil.openBrowser();
            }
        } catch (Exception e) {
            Global.getInstance().logError("InitSystemAfter init error", e);
        }
    }

    private void loadResourceToCache() {
        Global.getInstance().logInfo("Load Resource to Cache ...");
        Global.getInstance().getCacheManager().put(CachePool.RESOURCECACHE, "feep_login", FeepMvcKey.PAGEINDEXPATH);
    }

    private void loadSqltoCache() throws XmlException {
        Global.getInstance().logInfo("Load SQL to Cache ...");
        String[] sqlFiles = XmlMappingScanner.getAllMappingFilePath(Global.SQL_CONFIG_PATH);
        for (String path : sqlFiles) {
            FeepSqlMappingLoader daoLoader = new FeepSqlMappingLoader(path);
            Map<String, String> map = daoLoader.getAllSqls();
            Set<String> keys = map.keySet();
            for (String key : keys) {
                Global.getInstance().getCacheManager().put(CachePool.SQLCACHE, key, map.get(key));
            }
        }
    }

    private void loadUserToCache() throws FException {
        if (config.isAddUserToCache()) {
            Global.getInstance().logInfo("Load All Users to Cache ...");
            webAppContext.getBean(UserService.class).initUserToCache();
        }
    }

    private void loadFeepController() throws FException {
        /* 扫描指定包 */
        DefaultIocLoader defaultIocLoader = new DefaultIocLoader();
        List<Class<? extends Annotation>> list = new LinkedList<Class<? extends Annotation>>();
        list.add(FeepController.class);
        defaultIocLoader.setCustomAnnotationType(list);
        defaultIocLoader.load(Global.getInstance().getComponentScan());
        FeepControllerLoader loader = new FeepControllerLoader();
        loader.load(defaultIocLoader.getCustomAnnotationList(FeepController.class));
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent ctre) {
        this.webAppContext = (WebApplicationContext) ctre.getApplicationContext();
        init();
    }
}