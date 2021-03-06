package com.feit.feep.config.spring;

import com.feit.feep.cache.ehcache.CachePool;
import com.feit.feep.core.loader.IFeepBaseMenuLoader;
import com.feit.feep.core.loader.xml.BaseMenuLoader;
import com.feit.feep.core.resource.entity.FeepResource;
import com.feit.feep.mvc.entity.Menu;
import com.feit.feep.util.FeepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.feit.feep.core.Global;
import com.feit.feep.core.loader.message.MessageResource;
import com.feit.feep.core.loader.message.MultipleMessageSourceLoader;
import com.feit.feep.exception.FException;

import java.io.File;
import java.util.List;

@Configuration
public class SimpleBeans {
    @Autowired
    private ApplicationContext ctx;

    @Bean(name = "messageSource")
    public MultipleMessageSourceLoader getMultipleMessageSourceLoader() throws FException {
        MultipleMessageSourceLoader messageSource = new MultipleMessageSourceLoader();
        messageSource.setBasename("classpath:" + Global.MESSAGE_CONFIG_PATH);
        return messageSource;
    }

    @Bean(name = "localeResolver")
    public CookieLocaleResolver getCookieLocaleResolver() throws FException {
        return new CookieLocaleResolver();
    }

    @Bean(name = "messageResource")
    public MessageResource getMessageResource() throws FException {
        MessageResource messageResource = new MessageResource();
        messageResource.setApplicationContext(ctx);
        return messageResource;
    }

    @Bean(name = "baseMenu")
    public List<Menu> getBaseMenu() throws FException {
        File file = FeepUtil.getClassPathFile(Global.FEEP_PM);
        if (null != file) {
            IFeepBaseMenuLoader baseMenuLoader = new BaseMenuLoader(file);
            List<Menu> baseMenus = baseMenuLoader.getBaseMenus();
            loadMenuConfigToResourceCache(baseMenus, null);
            return baseMenus;
        }
        return null;
    }

    private void loadMenuConfigToResourceCache(List<Menu> baseMenus, String parentId) {
        if (!FeepUtil.isNull(baseMenus)) {
            for (int i = 0; i < baseMenus.size(); i++) {
                FeepResource feepResource = new FeepResource();
                String id = FeepUtil.getUUID();
                feepResource.setId(id);
                feepResource.setName(baseMenus.get(i).getName());
                feepResource.setUrl(baseMenus.get(i).getUrl());
                feepResource.setDisplay(baseMenus.get(i).getDisplay());
                feepResource.setSystem(Global.PROJECT_NAME);
                feepResource.setParentId(parentId);
                feepResource.setSort(i);
                Global.getInstance().getCacheManager().put(CachePool.RESOURCECACHE, feepResource.getId(), feepResource);
                if (!FeepUtil.isNull(baseMenus.get(i).getChildren())) {
                    loadMenuConfigToResourceCache(baseMenus.get(i).getChildren(), id);
                }
            }
        }
    }
}
