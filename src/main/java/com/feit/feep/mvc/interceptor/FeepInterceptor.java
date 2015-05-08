package com.feit.feep.mvc.interceptor;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.feit.feep.core.Global;
import com.feit.feep.mvc.entity.FeepMvcKey;
import com.feit.feep.system.entity.FeepUser;
import com.feit.feep.system.service.impl.UserService;
import com.feit.feep.util.FeepUtil;
import com.feit.feep.util.json.FeepJsonUtil;

/**
 * 拦截器
 * 
 * @author ZhangGang
 *
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
                if (MethodName.equals(FeepMvcKey.LOGINMETHODNAME)) {
                    return true;
                }
            }
            if (validateLogin()) {
                // 验证权限
                if (url.endsWith(FeepMvcKey.PATH_LINK)) {
                    // TODO
                }
                return true;
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

    private boolean validateLogin() {
        try {
            String userjson = (String) Global.getInstance().getRequest().getSession().getAttribute(FeepMvcKey.KEY_SESSION_USER);
            userjson = FeepUtil.simpleCryption(userjson, FeepMvcKey.CRYPTION_PUBLIC_KEY);
            FeepUser user = FeepJsonUtil.parseJson(userjson, FeepUser.class);
            if (null != user) {
                FeepUser dbuser = Global.getInstance().getApplicationContext().getBean(UserService.class).getUserById(user.getId());
                if (null != dbuser && dbuser.getPassword().equals(user.getPassword())) {
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
}