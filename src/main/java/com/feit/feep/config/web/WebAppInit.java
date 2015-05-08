package com.feit.feep.config.web;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.IntrospectorCleanupListener;
import org.springframework.web.util.Log4jConfigListener;

import com.feit.feep.config.spring.ApplicationConfig;
import com.feit.feep.core.Global;
import com.feit.feep.core.init.InitSystemBefore;

@Order(1)
public class WebAppInit implements WebApplicationInitializer {

    public void onStartup(ServletContext sc) throws ServletException {
        Global.getInstance().setTestJunit(false);
        /* 加载系统 */
        InitSystemBefore loadSystem = new InitSystemBefore();
        loadSystem.load(sc);
        /* Log4jConfigListener */
        sc.setInitParameter("log4jConfigLocation", Global.LOG4J_CONFIG_FILEPATH);
        sc.addListener(Log4jConfigListener.class);
        /* 字符编码过滤器 */
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding(Global.PROJECT_ENCODE);
        characterEncodingFilter.setForceEncoding(true);
        FilterRegistration filterRegistration = sc.addFilter("characterEncodingFilter", characterEncodingFilter);
        filterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");
        /* ApplicationConfig 加载 */
        AnnotationConfigWebApplicationContext webAppContext = new AnnotationConfigWebApplicationContext();
        webAppContext.register(ApplicationConfig.class);
        /* Spring 容器加载 */
        ContextLoaderListener contextLoader = new ContextLoaderListener(webAppContext);
        sc.addListener(contextLoader);
        /* 防止内存泄漏 */
        IntrospectorCleanupListener iClenupListener = new IntrospectorCleanupListener();
        sc.addListener(iClenupListener);
        /* MVC配置 */
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webAppContext);
        dispatcherServlet.setNamespace("/" + Global.getInstance().getFeepConfig().getContextPath());
        ServletRegistration.Dynamic dynamic = sc.addServlet("dispatcherServlet", dispatcherServlet);
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("*." + Global.PROJECT_ACTION_SUFFIX);
    }
}
