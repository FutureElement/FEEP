package com.feit.feep.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.feit.feep.exception.json.JsonException;

public class FeepJsonUtil {

    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
    };
    public static final String DATEFORMAT = "yyyy-MM-dd";
    public static final String DATETIMEFORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String toJson(Object o) throws JsonException {
        return JSON.toJSONString(o, features);
    }

    public static String toJson(Object o, String format) throws JsonException {
        return JSON.toJSONStringWithDateFormat(o, format, features);
    }

    public static <T> T parseJson(String json, Class<T> classType) throws JsonException {
        return JSON.parseObject(json, classType);
    }

    public static <T> T parseJson(String json, TypeReference<T> type) throws JsonException {
        return JSON.parseObject(json, type);
    }

}
