package com.feit.feep.mvc.util;

import com.feit.feep.exception.json.JsonException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.feit.feep.core.Global;
import com.feit.feep.mvc.entity.FeedBack;
import com.feit.feep.util.json.FeepJsonUtil;

/**
 * 响应工具
 * 
 * @author ZhangGang
 *
 */
public class ResponseUtil {

    private ResponseUtil() {

    }

    /**
     * 输出指定对象
     * 
     * @param
     * @return 返回ResponseEntity对象
     */
    public static ResponseEntity<String> responseSuccess() throws JsonException{
        FeedBack fb = new FeedBack();
        return responseSuccess(fb);
    }

    /**
     * 输出指定对象
     * 
     * @param
     * @return 返回ResponseEntity对象
     */
    public static ResponseEntity<String> responseSuccess(FeedBack fb) throws JsonException{
        HttpHeaders headers = getHttpHeaders();
        fb.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<String>(FeepJsonUtil.toJson(fb), headers, HttpStatus.OK);
    }

    /**
     * 输出指定对象
     * 
     * @param
     * @return 返回ResponseEntity对象
     */
    public static ResponseEntity<String> responseError() throws JsonException{
        FeedBack fb = new FeedBack();
        return responseError(fb);
    }

    /**
     * 输出指定对象
     * 
     * @param
     * @return 返回ResponseEntity对象
     */
    public static ResponseEntity<String> responseError(FeedBack fb) throws JsonException{
        HttpHeaders headers = getHttpHeaders();
        fb.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<String>(FeepJsonUtil.toJson(fb), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 得到Http头信息
     * 
     * @return Http头信息
     * @throws Exception
     */
    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("text", "html", Global.DEFAULT_CHARSET);
        headers.setContentType(mediaType);
        return headers;
    }

}
