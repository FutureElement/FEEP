package com.feit.feep.config.spring;

import com.feit.feep.core.loader.IFeepBaseMenuLoader;
import com.feit.feep.core.loader.xml.BaseMenuLoader;
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
            return baseMenuLoader.getBaseMenus();
        }
        return null;
    }

}
