package com.runchen.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//扫描mybatis通用的mapper包
@MapperScan(basePackages = {"com.runchen.blog.mapper"})
public class RunChenBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunChenBlogApplication.class, args);
    }
}
