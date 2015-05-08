package com.feit.feep.core.loader.message;

import java.util.Locale;

import org.springframework.context.ApplicationContext;

public class MessageResource {
    public static final Locale ZH_CN_LOCALE = new Locale("zh", "CN");
    public static final Locale EN_US_LOCALE = new Locale("en", "US");
    public static final Locale EN_LOCALE    = new Locale("en");

    private ApplicationContext ctx;

    public String getMessage(String key) {
        return ctx.getMessage(key, null, ZH_CN_LOCALE);
    }

    public String getMessage(String key, Object[] object) {
        return ctx.getMessage(key, object, ZH_CN_LOCALE);
    }

    public String getMessage(String key, Object[] object, Locale locale) {
        return ctx.getMessage(key, object, locale);
    }

    public void setApplicationContext(ApplicationContext app) {
        ctx = app;
    }
}
