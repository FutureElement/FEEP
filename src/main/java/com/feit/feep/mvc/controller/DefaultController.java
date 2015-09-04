package com.feit.feep.mvc.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feit.feep.exception.mvc.FeepControllerException;
import com.feit.feep.mvc.entity.Menu;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feit.feep.cache.ehcache.CachePool;
import com.feit.feep.core.Global;
import com.feit.feep.core.loader.entity.IocObject;
import com.feit.feep.core.loader.pool.FeepControllerIocPool;
import com.feit.feep.exception.ioc.IocException;
import com.feit.feep.mvc.entity.FeedBack;
import com.feit.feep.mvc.entity.FeepMvcKey;
import com.feit.feep.mvc.util.ResponseUtil;
import com.feit.feep.util.FeepUtil;
import com.feit.feep.util.json.FeepJsonUtil;

@Controller
public class DefaultController implements IDefaultController {

    private String link(String resourceName, ModelMap mm, HttpServletRequest request, HttpServletResponse response)
            throws FeepControllerException {
        try {
            mm.addAttribute(FeepMvcKey.CONTEXTPATH, request.getContextPath());
            mm.addAttribute(FeepMvcKey.PAGE_TITLE, Global.getInstance().getFeepConfig().getTitle());
            mm.addAttribute(FeepMvcKey.RESOURCENAME, resourceName);
            boolean isOpen = Boolean.valueOf(request.getParameter("isOpen"));
            if (!FeepUtil.isNull(resourceName)) {
                Object obj = Global.getInstance().getCacheManager().get(CachePool.RESOURCECACHE, resourceName);
                if (null != obj) {
                    return (String) obj;
                }
            }
            if (isOpen) {
                return FeepMvcKey.PAGE_404_OPEN_PATH;
            } else {
                ResponseUtil.redirect(request, response, FeepMvcKey.PAGE404_URL_LINK, null);
                return null;
            }
        } catch (Exception e) {
            throw new FeepControllerException("DefaultController page view error , resourceName:" + resourceName, e);
        }
    }

    @Override
    @RequestMapping("{resourceName}/link.feep")
    public String defaultLink(@PathVariable("resourceName") String resourceName, ModelMap mm, HttpServletRequest request, HttpServletResponse response) throws FeepControllerException {
        return link(resourceName, mm, request, response);
    }

    @Override
    @RequestMapping("pm/{resourceName}/link.feep")
    public String pmLink(@PathVariable("resourceName") String resourceName, ModelMap mm, HttpServletRequest request, HttpServletResponse response) throws FeepControllerException {
        List<Menu> baseMenus = Global.getInstance().getBaseMenus();
        if (resourceName.equals(FeepMvcKey.INDEX_RESOURCENAME)) {
            resourceName = getIndexResourceName(baseMenus);
            mm.addAttribute(FeepMvcKey.TopMenuIndex, 0);
        } else {
            String topMenuIndex = request.getParameter(FeepMvcKey.TopMenuIndex);
            if (FeepUtil.isNull(topMenuIndex)) {
                mm.addAttribute(FeepMvcKey.TopMenuIndex, 0);
            } else {
                mm.addAttribute(FeepMvcKey.TopMenuIndex, Integer.parseInt(topMenuIndex));
            }
        }
        //构建一级菜单
        mm.addAttribute(FeepMvcKey.TOPMENU, baseMenus);
        //构建二级菜单
        mm.addAttribute(FeepMvcKey.LEFTMENU, getSecondMenuByParentResourceName(baseMenus, resourceName));
        return link(resourceName, mm, request, response);
    }

    private String getIndexResourceName(List<Menu> menus) {
        String resourceName;
        if (!FeepUtil.isNull(menus.get(0).getChildren())) {
            resourceName = getIndexResourceName(menus.get(0).getChildren());
        } else {
            resourceName = menus.get(0).getName();
        }
        return resourceName;
    }


    private List<Menu> getSecondMenuByParentResourceName(List<Menu> menus, String resourceName) {
        for (Menu menu : menus) {
            String name = menu.getName();
            if (name.equals(resourceName)) {
                return menus;
            }
            if (!FeepUtil.isNull(menu.getChildren())) {
                List<Menu> nextLevel = getSecondMenuByParentResourceName(menu.getChildren(), resourceName);
                if (!FeepUtil.isNull(nextLevel)) {
                    return nextLevel;
                }
            }
        }
        return null;
    }


    @Override
    @RequestMapping("m/{resourceName}/link.feep")
    public String mLink(@PathVariable("resourceName") String resourceName, ModelMap mm, HttpServletRequest request, HttpServletResponse response) throws FeepControllerException {
        return link(resourceName, mm, request, response);
    }

    @Override
    @RequestMapping("service.feep")
    public ResponseEntity<String> service(HttpServletRequest request,
                                          HttpServletResponse response,
                                          String methodName,
                                          String parameters) throws FeepControllerException {
        try {
            IocObject<Method> iocObject = FeepControllerIocPool.getInstance().get(methodName);
            if (null != iocObject) {
                Method method = iocObject.getValue();
                Object[] methodParameters = null;
                if (!FeepUtil.isNull(parameters) && !parameters.equals("null")) {
                    methodParameters = FeepJsonUtil.parseArrayForDifferentTypes(parameters, method.getGenericParameterTypes());
                }
                Object instance = Global.getInstance().getApplicationContext().getBean(iocObject.getType());
                if ("void".equals(method.getReturnType().getName())) {
                    method.invoke(instance, methodParameters);
                    return ResponseUtil.responseSuccess();
                } else {
                    Object result = method.invoke(instance, methodParameters);
                    FeedBack fb = new FeedBack();
                    fb.setResult(result);
                    return ResponseUtil.responseSuccess(fb);
                }
            } else {
                throw new IocException("method not fount,MethodName : " + methodName);
            }
        } catch (Exception e) {
            Global.getInstance().logError("DefaultController call service error , methodName:" + methodName + ",parameters:" + parameters, e);
            return ResponseUtil.responseError(e.getMessage());
        }
    }

    @Override
    @RequestMapping("upload.feep")
    public void upload(HttpServletRequest request, HttpServletResponse response) throws FeepControllerException {
        // TODO Auto-generated method stub

    }

    @Override
    @RequestMapping("download.feep")
    public void download(HttpServletRequest request, HttpServletResponse response) throws FeepControllerException {
        // TODO Auto-generated method stub

    }

}
