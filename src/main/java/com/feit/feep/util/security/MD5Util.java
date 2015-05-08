package com.feit.feep.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    private MD5Util() {

    }

    public static String encryption(String plainText) throws NoSuchAlgorithmException{
        String re_md5 = null;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(plainText.getBytes());
        byte b[] = md.digest();
        int i;
        StringBuilder buf = new StringBuilder();
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        re_md5 = buf.toString();
        return re_md5.toUpperCase();
    }

}