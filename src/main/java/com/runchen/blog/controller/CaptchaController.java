package com.runchen.blog.controller;

import com.runchen.blog.common.Constants;
import com.runchen.blog.entity.bo.ImageCode;
import com.runchen.blog.util.RandomUtil;
import com.runchen.blog.util.VerifyCodeImageUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class CaptchaController {

    /**
     * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
     */
    @RequestMapping("/code/image")
    public void getVerifyCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置页面不缓存
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        String verifyCode = RandomUtil.randomStr(4);

        //将验证码放到HttpSession里面
        request.getSession().setAttribute(Constants.IMAGE_CODE, new ImageCode(verifyCode, 60));

        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = VerifyCodeImageUtil.generateImageCode(verifyCode);

        //写给浏览器
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(bufferedImage, "JPEG", outputStream);
        outputStream.close();
    }
}
