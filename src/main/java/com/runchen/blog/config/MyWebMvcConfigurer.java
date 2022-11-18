package com.runchen.blog.config;

import com.runchen.blog.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        // 添加一个拦截器，拦截以/admin为前缀的url路径
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/run_chen/**");
//                .excludePathPatterns("/admin/login")
//                .excludePathPatterns("/admin/plugins/**");
    }

//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/upload/**")
//                .addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 10 days
        addCacheControl(registry, "img", 864000);
        addCacheControl(registry, "vendor", 864000);
        // 1 day
        addCacheControl(registry, "scripts", 86400);
        addCacheControl(registry, "styles", 86400);
        addCacheControl(registry, "views", 86400);
    }

    private void addCacheControl(ResourceHandlerRegistry registry, String folder, int cachePeriod) {
        registry.addResourceHandler(String.format("/%s/**", folder))
                .addResourceLocations(String.format("classpath:/static/%s/", folder))
                .setCachePeriod(cachePeriod);
    }
}
