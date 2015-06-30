package com.feit.feep.mvc.interceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        Global.getInstance().logInfo("Validate Login");
        this.setEncoding(request, response);
        // TODO 返回没有权限页面
        return this.doFilter(request, response);
    }

    private boolean doFilter(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = request.getServletPath();
            // 方法验证
            if (url.equals(FeepMvcKey.PATH_SERVICE)) {
                String MethodName = request.getParameter(FeepMvcKey.METHODNAME);
                if (MethodName.equals(FeepMvcKey.LOGIN_METHODNAME)) {
                    return true;
                }
            }
            // 访问页面验证
            if (url.endsWith(FeepMvcKey.PATH_LINK)) {
                if (url.equals(FeepMvcKey.LOGIN_URL_LINK)) {
                    return true;
                }
                if (validateLogin(request)) {
                    return true;
                } else {
                    setMessagePrintOut(request, response);
                    return false;
                }
            }
        } catch (Exception e) {
            Global.getInstance().logError("FeepInterceptor doFilter", e);
        }
        return false;
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

    private boolean validateLogin(HttpServletRequest request) {
        try {
            String userjson = (String) request.getSession().getAttribute(FeepMvcKey.KEY_SESSION_USER);
            if (FeepUtil.isNull(userjson)) {
                return false;
            }
            userjson = FeepUtil.simpleCryption(userjson, FeepMvcKey.CRYPTION_PUBLIC_KEY);
            FeepUser user = FeepJsonUtil.parseJson(userjson, FeepUser.class);
            if (null != user) {
                FeepUser dbuser = Global.getInstance().getApplicationContext().getBean(IUserService.class).getUserById(user.getId());
                if (null != dbuser && dbuser.getPassword().equals(user.getPassword())) {
                    dbuser.setPassword(null);
                    request.setAttribute(FeepMvcKey.ATTR_LOGINUSER, dbuser);
                    return true;
                }
            }
        } catch (Exception e) {
            Global.getInstance().logError("get user from request error", e);
        }
        return false;
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
        if (request.getHeader("x-requested-with") == null) {
            response.getWriter().println("<script type='text/javascript'>"
                    + "alert('对不起，您尚未登录或者登录已超时，请重新登录!'); "
                    + "window.location.href='"
                    + request.getContextPath()
                    + FeepMvcKey.LOGIN_URL_LINK
                    + "';"
                    + "</script>");
        }
    }
}