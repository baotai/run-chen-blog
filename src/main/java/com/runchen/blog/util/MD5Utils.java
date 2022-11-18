package com.runchen.blog.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Utils {

    /**
     * 加密
     * @param str 明码
     * @return 密码
     */
    public static String encode(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new RuntimeException("MD5加密错误：" + e.getMessage(), e);
        }
    }

    /**
     * 加密
     * @param pwd 明码
     * @param salt 盐
     * @return 密码
     */
    public static String encode(String pwd, String salt) {
        String str = StringUtil.interleave(pwd, salt);
        return encode(str);
    }
}
