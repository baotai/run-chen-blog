package com.runchen.blog.util;

import org.springframework.util.StringUtils;

public class StringUtil {

    /**
     * 交叉合并两个字符串
     * @param str0 字符串
     * @param str1 字符串
     * @return 合并后字符串
     */
    public static String interleave(String str0, String str1) {

        if (!StringUtils.hasLength(str0) && !StringUtils.hasLength(str1)) {
            return "";
        }
        if (!StringUtils.hasLength(str0)) {
            return str1;
        }
        if (!StringUtils.hasLength(str1)) {
            return str0;
        }

        StringBuffer sb = new StringBuffer();

        int length = Math.min(str0.length(), str1.length());
        for (int i = 0; i < length; i++) {
            sb.append(str0.charAt(i)).append(str1.charAt(i));
        }

        if (str0.length() > length) {
            sb.append(str0.substring(length));
        }
        if (str1.length() > length) {
            sb.append(str1.substring(length));
        }

        return sb.toString();
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }
}
