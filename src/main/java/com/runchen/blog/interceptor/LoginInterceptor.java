package com.runchen.blog.interceptor;

import com.runchen.blog.common.Constants;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台系统身份验证拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (uri.startsWith("/run_chen") && null == request.getSession().getAttribute(Constants.LOGIN_USERS)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
     }
}
