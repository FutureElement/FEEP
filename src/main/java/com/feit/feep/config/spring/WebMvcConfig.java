package com.feit.feep.config.spring;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.feit.feep.core.Global;
import com.feit.feep.core.loader.entity.FeepConfig;
import com.feit.feep.mvc.interceptor.FeepInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    private ApplicationContext  ctx;

    private static final String MVC_PREFIX = "/WEB-INF/clients/";
    private static final String MVC_SUFFIX = ".jsp";

    @Bean(name = "servletHandlerAdapter")
    public HandlerAdapter servletHandlerAdapter() {
        return new SimpleServletHandlerAdapter();
    }

    @Bean(name = "fastJsonHttpMessageConverter")
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter fastjson = new FastJsonHttpMessageConverter();
        fastjson.setCharset(Global.DEFAULT_CHARSET);
        List<MediaType> supportedMediaTypes = new LinkedList<MediaType>();
        MediaType mtype = new MediaType("text", "plain", Global.DEFAULT_CHARSET);
        supportedMediaTypes.add(mtype);
        fastjson.setSupportedMediaTypes(supportedMediaTypes);
        // fastjson.setFeatures(features);
        return fastjson;
    }

    @Bean(name = "internalResourceViewResolver")
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver irv = new InternalResourceViewResolver();
        irv.setPrefix(MVC_PREFIX);
        irv.setSuffix(MVC_SUFFIX);
        return irv;
    }

    @Bean(name = "commonsMultipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver() {
        FeepConfig config = ctx.getBean("feepConfig", FeepConfig.class);
        CommonsMultipartResolver cmr = new CommonsMultipartResolver();
        cmr.setDefaultEncoding(Global.DEFAULT_CHARSET.name());
        cmr.setMaxUploadSize(config.getUploadConfig().getMaxUploadSize());
        cmr.setMaxInMemorySize(config.getUploadConfig().getMaxInMemorySize());
        return cmr;
    }

    @Bean(name = "requestMappingHandlerMapping")
    public RequestMappingHandlerMapping eequestMappingHandlerMapping() {
        RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
        handlerMapping.setInterceptors(new Object[]{new FeepInterceptor()});
        return handlerMapping;
    }

}
