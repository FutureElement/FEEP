package com.feit.feep.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.entity.EntityBeanSet;

/**
 * 全局工具类,静态方法
 *
 * @author ZhangGang
 */
public class FeepUtil {

    private FeepUtil() {

    }

    public static final String STRING_SEPARATOR = ",";

    public static boolean isNull(String str) {
        return null == str || str.trim().equals("");
    }

    public static boolean isNull(String... str) {
        if (null == str) {
            return true;
        } else if (str.length == 0) {
            return true;
        } else {
            for (String s : str) {
                if (isNull(s)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean isNull(Collection<?> collection) {
        return null == collection || collection.isEmpty();
    }

    public static boolean isNull(Object[] array) {
        return null == array || array.length == 0;
    }

    public static boolean isNull(EntityBeanSet ebs) {
        return null == ebs || ebs.size() == 0;
    }

    public static String toString(List<String> list) {
        return toString(list, STRING_SEPARATOR);
    }

    public static String toString(String[] array) {
        return toString(array, STRING_SEPARATOR);
    }

    public static String toString(List<String> list, String separator) {
        if (null == list || list.isEmpty()) {
            return null;
        }
        return toString(list.toArray(new String[list.size()]), separator);
    }

    public static String toString(String[] array, String separator) {
        if (null == array || array.length == 0) {
            return null;
        }
        StringBuilder buff = new StringBuilder();
        for (String s : array) {
            buff.append(s).append(separator);
        }
        return buff.substring(0, buff.length() - 1);
    }

    public static void closeInputStream(InputStream in) {
        try {
            if (null != in) {
                in.close();
            }
        } catch (IOException e) {
            Global.getInstance().logError("closeInputStream error", e);
        }
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public static File getClassPathFile(String path) {
        URL url = FeepUtil.class.getClassLoader().getResource(path);
        if (null != url) {
            return new File(url.getPath());
        }
        return null;
    }

    public static URL getClassPathURL(String path) {
        return FeepUtil.class.getClassLoader().getResource(path);
    }

    public static String simpleCryption(String inStr, String key) {
        if (FeepUtil.isNull(inStr)) {
            return null;
        }
        char[] a = inStr.toCharArray();
        char[] k = key.toCharArray();
        for (int i = 0; i < a.length; i++) {
            for (char aK : k) {
                a[i] = (char) (a[i] ^ aK);
            }
        }
        return new String(a);
    }

    public static String toUpperCaseFisrtWord(String input) {
        return input.replaceAll(input.charAt(0) + "", (input.charAt(0) + "").toUpperCase());
    }

    public static String toLowerCaseFisrtWord(String input) {
        return input.replaceAll(input.charAt(0) + "", (input.charAt(0) + "").toLowerCase());
    }
}