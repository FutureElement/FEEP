package com.feit.feep.mvc.util;

import com.feit.feep.config.sitemesh.SiteMeshFilter;
import com.feit.feep.dbms.entity.EntityBean;
import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.util.FeepUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.feit.feep.core.Global;
import com.feit.feep.mvc.entity.FeedBack;
import com.feit.feep.util.json.FeepJsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 响应工具
 *
 * @author ZhangGang
 */
public class ResponseUtil {

    private ResponseUtil() {

    }

    /**
     * 输出指定对象
     *
     * @return 返回ResponseEntity对象
     */
    public static ResponseEntity<String> responseSuccess() {
        FeedBack fb = new FeedBack();
        return responseSuccess(fb);
    }

    /**
     * 输出指定对象
     *
     * @param fb FeedBack
     * @return 返回ResponseEntity对象
     */
    public static ResponseEntity<String> responseSuccess(FeedBack fb) {
        try {
            String feedBack;
            HttpHeaders headers = getHttpHeaders();
            fb.setStatus(HttpStatus.OK.value());
            Object o = fb.getResult();
            String resultKey = "#resultKey2015#";
            if (o instanceof EntityBeanSet || o instanceof EntityBean) {
                fb.setResult(resultKey);
                feedBack = FeepJsonUtil.toJson(fb);
                feedBack = feedBack.replace("\"" + resultKey + "\"", o.toString());
            } else {
                feedBack = FeepJsonUtil.toJson(fb);
            }
            return new ResponseEntity<String>(feedBack, headers, HttpStatus.OK);
        } catch (Exception e) {
            Global.getInstance().logError(e);
            return null;
        }
    }

    /**
     * 输出指定对象
     *
     * @return 返回ResponseEntity对象
     */
    public static ResponseEntity<String> responseError(String msg) {
        FeedBack fb = new FeedBack();
        fb.setResult(msg);
        return responseError(fb);
    }

    /**
     * 输出指定对象
     *
     * @param fb FeedBack
     * @return 返回ResponseEntity对象
     */
    public static ResponseEntity<String> responseError(FeedBack fb) {
        try {
            HttpHeaders headers = getHttpHeaders();
            fb.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<String>(FeepJsonUtil.toJson(fb), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            Global.getInstance().logError(e);
            return null;
        }
    }

    /**
     * 得到Http头信息
     *
     * @return Http头信息
     */
    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("text", "html", Global.DEFAULT_CHARSET);
        headers.setContentType(mediaType);
        return headers;
    }

    /**
     * 通用跳转方法
     *
     * @param request   HttpServletRequest
     * @param response  HttpServletResponse
     * @param link      String
     * @param alertText alertText
     * @throws IOException
     */
    public static void redirect(HttpServletRequest request, HttpServletResponse response, String link, String alertText) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<" + SiteMeshFilter.REDIRECT_TAG + "><script>");
        if (!FeepUtil.isNull(alertText)) {
            stringBuilder.append("alert('");
            stringBuilder.append(alertText);
            stringBuilder.append("'); ");
        }
        stringBuilder.append("window.location.href='");
        stringBuilder.append(request.getContextPath());
        stringBuilder.append(link);
        stringBuilder.append("';");
        stringBuilder.append("</script></" + SiteMeshFilter.REDIRECT_TAG + ">");
        response.getWriter().println(stringBuilder.toString());
    }
}
