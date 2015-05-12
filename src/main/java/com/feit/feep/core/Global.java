package com.feit.feep.core;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.feit.feep.cache.FeepCacheManager;
import com.feit.feep.cache.ehcache.EhcacheManager;
import com.feit.feep.core.loader.entity.FeepConfig;
import com.feit.feep.core.loader.message.MessageResource;
import com.feit.feep.core.log.Log;
import com.feit.feep.mvc.entity.FeepMvcKey;
import com.feit.feep.system.entity.FeepUser;
import com.feit.feep.system.service.impl.UserService;
import com.feit.feep.util.FeepUtil;
import com.feit.feep.util.json.FeepJsonUtil;

public class Global {

    public static final String  PROJECT_APPCONFIG_FILEPATH = "/FEEP.xml";
    public static final String  PROJECT_ACTION_SUFFIX      = "feep";
    public static final String  PROJECT_ENCODE             = "UTF-8";

    public static final String  PROJECT_SCANNER_FILEPATH   = "FeepResource/scanner";
    public static final String  CACHE_CONFIG_PATH          = "FeepResource/cache/ehcache.xml";
    public static final String  SQL_CONFIG_PATH            = "FeepResource/sql";
    public static final String  MESSAGE_CONFIG_PATH        = "FeepResource/message";
    public static final String  LOG4J_CONFIG_FILEPATH      = "classpath:log4j.properties";

    public static final Charset DEFAULT_CHARSET            = Charset.forName(PROJECT_ENCODE);

    private static Global       instance                   = new Global();

    private boolean             testJunit;

    private ApplicationContext  ctx;

    private Global() {

    }

    public void setApplicationContext(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    public ApplicationContext getApplicationContext() {
        return ctx;
    }

    public static Global getInstance() {
        return instance;
    }

    public FeepCacheManager getCacheManager() {
        return ctx.getBean("cacheManager", EhcacheManager.class);
    }

    public MessageResource getMessageResource() {
        return ctx.getBean("messageResource", MessageResource.class);
    }

    public FeepConfig getFeepConfig() {
        return ctx.getBean("feepConfig", FeepConfig.class);
    }

    public void logError(Object o) {
        logError(o, this.getClass());
    }

    public void logError(Object o, Throwable t) {
        logError(o, this.getClass(), t);
    }

    public void logError(Object o, Class<?> classType) {
        Log.getInstance().logError(o, classType);
    }

    public void logError(Object o, Class<?> classType, Throwable t) {
        Log.getInstance().logError(o, classType, t);
    }

    public void logInfo(Object o) {
        logInfo(o, this.getClass());
    }

    public void logInfo(Object o, Throwable t) {
        logInfo(o, this.getClass(), t);
    }

    public void logInfo(Object o, Class<?> classType) {
        Log.getInstance().logInfo(o, classType);
    }

    public void logInfo(Object o, Class<?> classType, Throwable t) {
        Log.getInstance().logInfo(o, classType, t);
    }

    public void logWarn(Object o) {
        logWarn(o, this.getClass());
    }

    public void logWarn(Object o, Throwable t) {
        logWarn(o, this.getClass(), t);
    }

    public void logWarn(Object o, Class<?> classType) {
        Log.getInstance().logWarn(o, classType);
    }

    public void logWarn(Object o, Class<?> classType, Throwable t) {
        Log.getInstance().logWarn(o, classType, t);
    }

    public void logFatal(Object o) {
        logFatal(o, this.getClass());
    }

    public void logFatal(Object o, Throwable t) {
        logFatal(o, this.getClass(), t);
    }

    public void logFatal(Object o, Class<?> classType) {
        Log.getInstance().logFatal(o, classType);
    }

    public void logFatal(Object o, Class<?> classType, Throwable t) {
        Log.getInstance().logFatal(o, classType, t);
    }

    /**
     * 获得request
     * 
     * @return HttpServletRequest
     */
    public HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }

    /**
     * 获得Response
     * 
     * @return HttpServletResponse
     */
    public HttpServletResponse getResponse() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getResponse();
    }

    /**
     * 获得当前登陆用户
     * 
     * @return
     */
    public FeepUser getLoginUser() {
        FeepUser user = null;
        try {
            String userjson = (String) getRequest().getSession().getAttribute(FeepMvcKey.KEY_SESSION_USER);
            userjson = FeepUtil.simpleCryption(userjson, FeepMvcKey.CRYPTION_PUBLIC_KEY);
            FeepUser sessionUser = FeepJsonUtil.parseJson(userjson, FeepUser.class);
            user = ctx.getBean(UserService.class).getUserById(sessionUser.getId());
        } catch (Exception e) {
            Global.getInstance().logError("get user from request error", e);
        }
        return user;
    }

    public boolean isTestJunit() {
        return testJunit;
    }

    public void setTestJunit(boolean testJunit) {
        this.testJunit = testJunit;
    }

}