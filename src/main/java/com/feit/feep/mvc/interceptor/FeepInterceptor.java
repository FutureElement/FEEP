package com.feit.feep.mvc.interceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feit.feep.mvc.util.ResponseUtil;
import com.feit.feep.system.service.IUserService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.feit.feep.core.Global;
import com.feit.feep.mvc.entity.FeepMvcKey;
import com.feit.feep.system.entity.FeepUser;
import com.feit.feep.util.FeepUtil;
import com.feit.feep.util.json.FeepJsonUtil;

/**
 * 拦截器
 *
 * @author ZhangGang
 */
public class FeepInterceptor implements HandlerInterceptor {

    private static final String CONTENT_TYPE = "text/html;charset=utf-8";

    /**
     * 请求处理之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isPass = true;
        try {
            Global.getInstance().logInfo("Validate Login");
            this.setEncoding(request, response);
            boolean needCheck = true;
            String url = request.getServletPath();
            if (url.endsWith(FeepMvcKey.PATH_LINK)) {
                // 访问页面验证
                if (url.equals(FeepMvcKey.LOGIN_URL_LINK)) {
                    needCheck = false;
                }
            } else if (url.equals(FeepMvcKey.PATH_SERVICE)) {
                // 方法验证
                String MethodName = request.getParameter(FeepMvcKey.METHODNAME);
                if (MethodName.equals(FeepMvcKey.LOGIN_METHODNAME)) {
                    needCheck = false;
                }
            }
            if (needCheck) {
                isPass = validateLogin(request, response);
            }
            if (!isPass) {
                setMessagePrintOut(request, response);
            }
        } catch (Exception e) {
            isPass = false;
            Global.getInstance().logError("validate Login error", e);
        }
        return isPass;
    }

    /**
     * 请求处理完成后，响应客户端前
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        // TODO Auto-generated method stub
        FeepUser user = (FeepUser) request.getAttribute(FeepMvcKey.ATTR_LOGINUSER);
        request.removeAttribute(FeepMvcKey.ATTR_LOGINUSER);
        if (null != modelAndView) {
            modelAndView.addObject(FeepMvcKey.ATTR_LOGINUSER, user);
        }
        Global.getInstance().logInfo("request handle");
    }

    /**
     * 请求完全处理完成后执行
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        Global.getInstance().logInfo("after response");
    }

    private boolean validateLogin(HttpServletRequest request, HttpServletResponse response) {
        boolean ret = false;
        try {
            String userjson = (String) request.getSession().getAttribute(FeepMvcKey.KEY_SESSION_USER);
            if (FeepUtil.isNull(userjson)) {
                ret = false;
            }
            FeepUser user = null;
            userjson = FeepUtil.simpleCryption(userjson, FeepMvcKey.CRYPTION_PUBLIC_KEY);
            if (!FeepUtil.isNull(userjson)) {
                user = FeepJsonUtil.parseJson(userjson, FeepUser.class);
            }
            if (null != user) {
                FeepUser dbuser = Global.getInstance().getApplicationContext().getBean(IUserService.class).getUserById(user.getId());
                if (null != dbuser && dbuser.getPassword().equals(user.getPassword())) {
                    request.setAttribute(FeepMvcKey.ATTR_LOGINUSER, dbuser);
                    ret = true;
                }
            }
        } catch (Exception e) {
            ret = false;
            Global.getInstance().logError("get user from request error", e);
        }
        return ret;
    }

    /**
     * 设置request，response字符集 否则输出的script会乱码
     *
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    private void setEncoding(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding(Global.PROJECT_ENCODE);
        response.setContentType(CONTENT_TYPE);
    }

    /**
     * 返回提示
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void setMessagePrintOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Global.getInstance().logInfo("Validate Login false");
        if (request.getHeader("x-requested-with") == null) {
            ResponseUtil.redirect(request, response, FeepMvcKey.LOGIN_URL_LINK, "对不起，您尚未登录或者登录已超时，请重新登录!");
        }
    }
}