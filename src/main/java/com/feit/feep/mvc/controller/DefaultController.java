package com.feit.feep.mvc.controller;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feit.feep.core.resource.entity.FeepResource;
import com.feit.feep.dbms.crud.DefaultDao;
import com.feit.feep.dbms.entity.query.Condition;
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
            throws Exception {
        mm.addAttribute(FeepMvcKey.CONTEXTPATH, request.getContextPath());
        mm.addAttribute(FeepMvcKey.PAGE_TITLE, Global.getInstance().getFeepConfig().getTitle());
        mm.addAttribute(FeepMvcKey.RESOURCENAME, feepResource.getName());
        boolean isOpen = Boolean.valueOf(request.getParameter("isOpen"));
        if (!FeepUtil.isNull(resourceName)) {
            List<FeepResource> feepResources = Global.getInstance().getCacheManager().findByAttribute(CachePool.RESOURCECACHE, FeepResource.COL_NAME, resourceName, FeepResource.class);
            if (!FeepUtil.isNull(feepResources)) {
                return feepResources.get(0).getUrl();
            }
        }
        if (isOpen) {
            return FeepMvcKey.PAGE_404_OPEN_PATH;
        } else {
            ResponseUtil.redirect(request, response, FeepMvcKey.PAGE404_URL_LINK, null);
            return null;
        }
    }

    @Override
    @RequestMapping("{resourceName}/link.feep")
    public String defaultLink(@PathVariable("resourceName") String resourceName, ModelMap mm, HttpServletRequest request, HttpServletResponse response) throws FeepControllerException {
        try {
            return link(resourceName, mm, request, response);
        } catch (Exception e) {
            throw new FeepControllerException("DefaultController page view error , resourceName:" + resourceName, e);
        }
    }

    @Override
    @RequestMapping("pm/{resourceName}/link.feep")
    public String pmLink(@PathVariable("resourceName") String resourceName, ModelMap mm, HttpServletRequest request, HttpServletResponse response) throws FeepControllerException {
        try {
            List<Menu> baseMenus = Global.getInstance().getBaseMenus();
            if (resourceName.equals(FeepMvcKey.INDEX_RESOURCENAME)) {
                resourceName = getIndexResourceName(baseMenus);
            }
            //构建一级菜单
            mm.addAttribute(FeepMvcKey.TOPMENU, baseMenus);
            //构建二级菜单
            List<FeepResource> feepResources = Global.getInstance().getCacheManager().findByAttribute(CachePool.RESOURCECACHE, FeepResource.COL_NAME, resourceName, FeepResource.class);
            FeepResource selectedRs = feepResources.get(0);
            String parentId;
            if (FeepUtil.isNull(selectedRs.getParentId())) {
                parentId = selectedRs.getId();
                mm.addAttribute(FeepMvcKey.TOPMENU_NAME, resourceName);
            } else {
                parentId = selectedRs.getParentId();
                FeepResource parent = Global.getInstance().getCacheManager().get(CachePool.RESOURCECACHE, parentId, FeepResource.class);
                mm.addAttribute(FeepMvcKey.TOPMENU_NAME, parent.getName());
            }
            List<FeepResource> leftMenus = getChildrenResource(parentId);
            if (FeepUtil.isNull(leftMenus)) {
                mm.addAttribute(FeepMvcKey.LEFTMENU, null);
            } else {
                mm.addAttribute(FeepMvcKey.LEFTMENU, leftMenus);
            }
            return link(resourceName, mm, request, response);
        } catch (Exception e) {
            throw new FeepControllerException("DefaultController page view error , resourceName:" + resourceName, e);
        }
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
    
    @Override
    @RequestMapping("m/{resourceName}/link.feep")
    public String mLink(@PathVariable("resourceName") String resourceName, ModelMap mm, HttpServletRequest request, HttpServletResponse response) throws FeepControllerException {
        try {
            return link(resourceName, mm, request, response);
        } catch (Exception e) {
            throw new FeepControllerException("DefaultController page view error , resourceName:" + resourceName, e);
        }
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

    private List<FeepResource> getChildrenResource(String parentId) throws Exception {
        DefaultDao defaultDao = new DefaultDao();
        defaultDao.addQueryParameter(FeepResource.COL_PARENTID, parentId, Condition.EQUALS);
        defaultDao.addSortField(FeepResource.COL_SORT, true);
        return Global.getInstance().getCacheManager().queryCache(CachePool.RESOURCECACHE, defaultDao.getFeepQueryBean(), FeepResource.class);
    }
}
