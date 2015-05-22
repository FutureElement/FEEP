package com.feit.feep.core.init;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.feit.feep.cache.ehcache.CachePool;
import com.feit.feep.core.Global;
import com.feit.feep.core.annotation.FeepController;
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
public class InitSystemAfter implements
		ApplicationListener<ContextRefreshedEvent> {

	private ApplicationContext ctx;

	private FeepConfig config;

	public void init() {
		Global.getInstance().logInfo("FEEP System init...", this.getClass());
		try {
			config = Global.getInstance().getFeepConfig();
			/* 1.加载SQL到缓存 */
			loadSqltoCache();
			/* 2.初始化表*/
			initDataTables();
			/* 3.加载用户到缓存 */
			loadUserToCache();
			/* 4.加载 Resource */
			loadResourceToCache();
			/* 5.加载 FeepController */
			loadFeepController();
			/* 开启浏览器 */
			boolean isTest = Global.getInstance().isTestJunit();
			if (!isTest) {
				BrowserUtil.openBrowser();
			}
		} catch (Exception e) {
			Global.getInstance().logError("InitSystemAfter init error", e);
		}
	}

	private void initDataTables() {
		//TODO 
	}

	private void loadResourceToCache() {
		Global.getInstance().logInfo("Load Resource to Cache ...");
		Global.getInstance()
				.getCacheManager()
				.put(CachePool.RESOURCECACHE, "feep_index",
						FeepMvcKey.PAGE_INDEX_PATH);
		Global.getInstance()
				.getCacheManager()
				.put(CachePool.RESOURCECACHE, "feep_login",
						FeepMvcKey.PAGE_LOGIN_PATH);
	}

	private void loadSqltoCache() throws XmlException {
		Global.getInstance().logInfo("Load SQL to Cache ...");
		String[] sqlFiles = XmlMappingScanner
				.getAllMappingFilePath(Global.SQL_CONFIG_PATH);
		for (String path : sqlFiles) {
			FeepSqlMappingLoader daoLoader = new FeepSqlMappingLoader(path);
			Map<String, String> map = daoLoader.getAllSqls();
			Set<String> keys = map.keySet();
			for (String key : keys) {
				Global.getInstance().getCacheManager()
						.put(CachePool.SQLCACHE, key, map.get(key));
			}
		}
	}

	private void loadUserToCache() throws FException {
		if (config.isAddUserToCache()) {
			Global.getInstance().logInfo("Load All Users to Cache ...");
			ctx.getBean(UserService.class).initUserToCache();
		}
	}

	private void loadFeepController() throws FException {
		Global.getInstance().logInfo("loadFeepController", this.getClass());
		Map<String, Object> map = ctx
				.getBeansWithAnnotation(FeepController.class);
		if (null != map) {
			Set<String> keys = map.keySet();
			if (null != keys) {
				FeepControllerLoader loader = new FeepControllerLoader();
				List<Class<?>> list = new LinkedList<Class<?>>();
				for (String key : keys) {
					Object obj = map.get(key);
					if (null != obj) {
						list.add(obj.getClass());
					}
				}
				loader.load(list);
			}
		}
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent ctre) {
		this.ctx = ctre.getApplicationContext();
		init();
	}
}