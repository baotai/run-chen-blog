package com.runchen.blog.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class VerifyCodeImageUtil {

    /**
     * 已有验证码,生成验证码图片
     *
     * @param textCode       文本验证码
     * @param width          图片宽度(注意此宽度若过小,容易造成验证码文本显示不全,如4个字符的文本可使用85到90的宽度)
     * @param height         图片高度
     * @param interLine      图片中干扰线的条数
     * @param randomLocation 每个字符的高低位置是否随机
     * @param backColor      图片颜色,若为null则表示采用随机颜色
     * @param foreColor      字体颜色,若为null则表示采用随机颜色
     * @param lineColor      干扰线颜色,若为null则表示采用随机颜色
     * @return 图片缓存对象
     */
    public static BufferedImage generateImageCode(String textCode, int width, int height, int interLine,
                                                  boolean randomLocation, Color backColor, Color foreColor, Color lineColor) {
        //创建内存图像
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //获取图形上下文
        Graphics graphics = bufferedImage.getGraphics();
        //画背景图
        graphics.setColor(null == backColor ? RandomUtil.randomColor() : backColor);
        graphics.fillRect(0, 0, width, height);
        //画干扰线
        if (interLine > 0) {
            int x = 0, y, y1;
            for (int i = 0; i < interLine; i++) {
                graphics.setColor(null == lineColor ? RandomUtil.randomColor() : lineColor);
                y = RandomUtil.randomNumber(width);
                y1 = RandomUtil.randomNumber(height);
                graphics.drawLine(x, y, width, y1);
            }
        }
        //字体大小为图片高度的80%
        int fsize = (int) (height * 0.7);
        int fx = height - fsize;
        int fy = fsize;
        //设定字体
        graphics.setFont(new Font("Default", Font.PLAIN, fsize));
        //写验证码字符
        for (int i = 0; i < textCode.length(); i++) {
            fy = randomLocation ? (int) ((Math.random() * 0.3 + 0.6) * height) : fy;
            graphics.setColor(null == foreColor ? RandomUtil.randomColor() : foreColor);
            //将验证码字符显示到图象中
            graphics.drawString(textCode.charAt(i) + "", fx, fy);
            fx += fsize * 0.9;
        }
        graphics.dispose();
        return bufferedImage;
    }

    /**
     * 生成验证码图片
     *
     * @param textCode 图片内字符串
     * @return BufferedImage
     */
    public static BufferedImage generateImageCode(String textCode) {
        int width = 120;
        int height = 40;
        int interLine = 13;
        boolean randomLocation = true;
        Color backColor = Color.WHITE;
        Color foreColor = Color.BLACK;
        Color lineColor = null;
        return generateImageCode(textCode, width, height, interLine, randomLocation, backColor, foreColor, lineColor);
    }
}
