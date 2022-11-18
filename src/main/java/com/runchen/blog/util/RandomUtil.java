package com.runchen.blog.util;

import java.awt.*;
import java.util.Random;

public class RandomUtil {

    /**
     * 随机生成字符串
     *
     * @param length 字符串的长度
     * @param bound  1：大写字符串，2：字母字符串；3：数字字母字符串；other:
     * @return 符串
     */
    public static String randomStr(int length, int bound) {

        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; i++) {
            //随机生成一个整数，这个整数的范围就是[0,3）
            int number = random.nextInt(bound);
            long result;
            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);//大写字符
                    sb.append((char) result);
                    break;
                case 1:
                    result = Math.round(Math.random() * 25 + 97);//小写字符
                    sb.append((char) result);
                    break;
                case 2:
                    sb.append(new Random().nextInt(10));//数字
                    break;
            }


        }
        return sb.toString();
    }

    /**
     * 随机生成由字母和数字组成的字符串
     *
     * @param length 字符串长度
     * @return 字符串
     */
    public static String randomStr(int length) {
        return randomStr(length, 3);
    }

    /**
     * 生成随机颜色
     */
    public static Color randomColor() {
        Random random = new Random();
        return new Color(random.nextInt(255),
                random.nextInt(255),
                random.nextInt(255));
    }

    /**
     * 随机生成一个数
     *
     * @param bound 范围就是[0,bound）
     * @return Integer
     */
    public static Integer randomNumber(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }
}
