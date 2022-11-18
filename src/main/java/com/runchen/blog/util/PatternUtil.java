package com.runchen.blog.util;

import com.runchen.blog.common.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具类
 */
public class PatternUtil {

    /**
     * 匹配邮箱正则
     */
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(Constants.EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

    /**
     * 验证只包含中英文和数字的字符串
     *
     * @param keyword
     * @return
     */
    public static Boolean validKeyword(String keyword) {
        Pattern pattern = Pattern.compile(Constants.KEYWORD_REGEX);
        Matcher match = pattern.matcher(keyword);
        return match.matches();
    }

    /**
     * 判断是否是邮箱
     *
     * @param emailStr
     * @return
     */
    public static boolean isEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    /**
     * 判断是否是网址
     *
     * @param urlString
     * @return
     */
    public static boolean isURL(String urlString) {
        Pattern pattern = Pattern.compile(Constants.URL_REGEX);
        return pattern.matcher(urlString).matches();
    }
}
