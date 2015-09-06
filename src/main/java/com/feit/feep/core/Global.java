package com.feit.feep.core;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.feit.feep.dbms.util.MultiDataSource;
import com.feit.feep.mvc.entity.Menu;

import net.sf.ehcache.TransactionController;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;
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
    public static final String PROJECT_NAME = "FEEP";
    public static final String PROJECT_APPCONFIG_FILEPATH = "/FEEP.xml";
    public static final String PROJECT_ACTION_SUFFIX = "feep";
    public static final String PROJECT_ENCODE = "UTF-8";

    public static final String CACHE_CONFIG_PATH = "FeepResource/cache/ehcache.xml";
    public static final String SQL_CONFIG_PATH = "FeepResource/sql";
    public static final String FEEP_PM = "FeepResource/pm/BaseMenu.xml";
    public static final String MESSAGE_CONFIG_PATH = "FeepResource/message";
    public static final String LOG4J_CONFIG_FILEPATH = "classpath:log4j.properties";

    public static final Charset DEFAULT_CHARSET = Charset.forName(PROJECT_ENCODE);

    private static Global instance = new Global();

    private boolean testJunit;

    private ApplicationContext ctx;

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

    public TransactionController getCacheTransaction() {
        return ctx.getBean("cacheManager", EhcacheManager.class).getTransaction();
    }

    public MessageResource getMessageResource() {
        return ctx.getBean("messageResource", MessageResource.class);
    }

    public FeepConfig getFeepConfig() {
        return ctx.getBean("feepConfig", FeepConfig.class);
    }

    public void logError(String msg) {
        Log.getInstance().logError(msg, this.getClass());
    }

    public void logError(Throwable t) {
        Log.getInstance().logError(t, this.getClass());
    }

    public void logError(String msg, Throwable t) {
        Log.getInstance().logError(msg, t, this.getClass());
    }

    public void logInfo(String msg) {
        Log.getInstance().logInfo(msg, this.getClass());
    }

    public void logInfo(Throwable t) {
        Log.getInstance().logInfo(t, this.getClass());
    }

    public void logInfo(String msg, Throwable t) {
        Log.getInstance().logInfo(msg, t, this.getClass());
    }

    public void logWarn(String msg) {
        Log.getInstance().logWarn(msg, this.getClass());
    }

    public void logWarn(Throwable t) {
        Log.getInstance().logWarn(t, this.getClass());
    }

    public void logWarn(String msg, Throwable t) {
        Log.getInstance().logWarn(msg, t, this.getClass());
    }

    public void logFatal(String msg) {
        Log.getInstance().logFatal(msg, this.getClass());
    }

    public void logFatal(Throwable t) {
        Log.getInstance().logFatal(t, this.getClass());
    }

    public void logFatal(String msg, Throwable t) {
        Log.getInstance().logFatal(msg, t, this.getClass());
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
            Global.getInstance().logError("no user login or get user from request error", e);
        }
        return user;
    }

    public boolean isTestJunit() {
        return testJunit;
    }

    public void setTestJunit(boolean testJunit) {
        this.testJunit = testJunit;
    }

    public JdbcTemplate getJdbcTemplate(String name) {
        return MultiDataSource.getInstance().getJdbcTemplate(name);
    }

    public DataSource getDataSource(String name) {
        return MultiDataSource.getInstance().getDataSource(name);
    }

    public TransactionTemplate getTransactionTemplate(String name) {
        return MultiDataSource.getInstance().getTransactionTemplate(name);
    }

    @SuppressWarnings("unchecked")
    public List<Menu> getBaseMenus() {
        return (List<Menu>) ctx.getBean("baseMenu");
    }
}