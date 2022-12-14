package com.runchen.blog.controller;

import com.runchen.blog.common.Constants;
import com.runchen.blog.common.Result;
import com.runchen.blog.entity.bo.ImageCode;
import com.runchen.blog.entity.po.Users;
import com.runchen.blog.service.UserService;
import com.runchen.blog.util.MD5Utils;
import com.runchen.blog.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpServletRequest request) {

        //检查验证码
        String result = validate(verifyCode, request);
        if (StringUtils.hasLength(result)) {
            return Result.errorMsg(result);
        }
        if (!StringUtils.hasLength(username)) {
            return Result.errorMsg("请填写用户名");
        }

        Users users = userService.findByUsername(username);
        if (users == null) {
            return Result.errorMsg("用户名不存在");
        }
        String pwd = MD5Utils.encode(password, users.getSalt());
        if (!pwd.equals(users.getPassword())) {
            return Result.errorMsg("用户名或密码不正确");
        }

        request.getSession().setAttribute(Constants.LOGIN_USERS, users);
        return Result.ok();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Result logout(HttpServletRequest request) {
        request.getSession().removeAttribute(Constants.LOGIN_USERS);
        return Result.ok();
    }

    private String validate(String verifyCode, HttpServletRequest request) {
//        SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
        //从session中取出 验证码
        HttpSession session = request.getSession();
        ImageCode codeInSession = (ImageCode) session.getAttribute(Constants.IMAGE_CODE);

        if (!StringUtils.hasLength(verifyCode)) {
            return "验证码不能为空";
        }
        if (codeInSession == null) {
            logger.info("==========================验证码不存在");
            return "验证码不存在";
        }
        if (codeInSession.isExpired()) {
            logger.info("==========================验证码已过期");
            return "验证码已过期";
        }

        if (!StringUtil.equalsIgnoreCase(codeInSession.getCode(), verifyCode)) {
            logger.info("==========================验证码不匹配");
            return "验证码不匹配";
        }
        session.removeAttribute(Constants.IMAGE_CODE);
        return "";
    }

}
