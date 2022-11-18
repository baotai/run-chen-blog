package com.runchen.blog.controller.member;

public abstract class BaseController {

    public static Integer initCurrentPage(Integer currentPage) {
        return  (currentPage == null || currentPage < 1) ? 1 : currentPage;
    }

    public static Integer initPageSize(Integer pageSize) {
        return (pageSize == null || pageSize < 1) ? 10 : pageSize;
    }
}
