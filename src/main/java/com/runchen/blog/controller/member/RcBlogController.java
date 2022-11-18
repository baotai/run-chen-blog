package com.runchen.blog.controller.member;

import com.runchen.blog.common.PagedResult;
import com.runchen.blog.common.Result;
import com.runchen.blog.entity.po.RcBlog;
import com.runchen.blog.service.RcBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/run_chen/blog")
public class RcBlogController extends BaseController {

    @Autowired
    private RcBlogService rcBlogService;

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Result findById(@RequestParam("id") Long id) {

        if (id <= 0) {
            return Result.errorMsg("id不能为空！");
        }

        RcBlog entity = rcBlogService.findById(id);

        return Result.ok(entity);
    }

    @RequestMapping(value = "/findList", method = RequestMethod.POST)
    public Result findList(@RequestBody RcBlog rcBlog) {
        List<RcBlog> list = rcBlogService.findByKeyword(rcBlog);
        return Result.ok(list);
    }

    @RequestMapping(value = "/fuzzySelect", method = RequestMethod.POST)
    public Result fuzzySelect(@RequestBody RcBlog rcBlog,
                              @RequestParam("currentPage") Integer currentPage,
                              @RequestParam("pageSize") Integer pageSize) {

        if (rcBlog == null) {
            rcBlog = new RcBlog();
        }

        currentPage = initCurrentPage(currentPage);
        pageSize = initPageSize(pageSize);

        PagedResult<RcBlog> pagedResult = rcBlogService.fuzzySelect(rcBlog, currentPage, pageSize);

        return Result.ok(pagedResult);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody RcBlog rcBlog) {
        if (rcBlog == null) {
            return Result.errorMsg("不能为空！");
        }
        if (!StringUtils.hasLength(rcBlog.getTitle())) {
            return Result.errorMsg("标题不能为空！");
        }
        if (!StringUtils.hasLength(rcBlog.getBlogContent())) {
            return Result.errorMsg("内容不能为空！");
        }
        return rcBlogService.save(rcBlog);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateById(@RequestBody RcBlog rcBlog) {
        if (rcBlog == null || rcBlog.getId() <= 0) {
            return Result.errorMsg("Id不能为空！");
        }
        return rcBlogService.updateById(rcBlog);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestBody RcBlog rcBlog) {
        if (rcBlog == null || rcBlog.getId() <= 0) {
            return Result.errorMsg("Id不能为空！");
        }
        return rcBlogService.delete(rcBlog);
    }
}
