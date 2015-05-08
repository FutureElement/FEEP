package com.feit.feep.config.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.feit.feep.core.Global;
import com.feit.feep.core.loader.message.MessageResource;
import com.feit.feep.core.loader.message.MultipleMessageSourceLoader;
import com.feit.feep.exception.FException;

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

}
