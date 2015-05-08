package com.feit.feep.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.feit.feep.exception.json.JsonException;

public class FeepJsonUtil {

    private FeepJsonUtil() {

    }

    public static final String DATEFORMAT     = "yyyy-MM-dd";
    public static final String DATETIMEFORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String toJson(Object o) throws JsonException {
        return JSON.toJSONString(o);
    }

    public static String toJson(Object o, String format) throws JsonException {
        return JSON.toJSONStringWithDateFormat(o, format);
    }

    public static <T> T parseJson(String json, Class<T> classType) throws JsonException {
        return JSON.parseObject(json, classType);
    }

    public static <T> T parseJson(String json, TypeReference<T> type) throws JsonException {
        return JSON.parseObject(json, type);
    }

}
