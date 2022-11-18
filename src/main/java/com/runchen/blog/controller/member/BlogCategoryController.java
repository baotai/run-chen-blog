package com.runchen.blog.controller.member;

import com.runchen.blog.common.PagedResult;
import com.runchen.blog.common.Result;
import com.runchen.blog.entity.po.BlogCategory;
import com.runchen.blog.service.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/run_chen/blog/category")
public class BlogCategoryController extends BaseController {

    @Autowired
    private BlogCategoryService blogCategoryService;

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Result findById(@RequestParam("id") Long id) {

        if (id <= 0) {
            return Result.errorMsg("id不能为空！");
        }

        BlogCategory entity = blogCategoryService.findById(id);

        return Result.ok(entity);
    }

    @RequestMapping(value = "/findList", method = RequestMethod.POST)
    public Result findList(@RequestBody BlogCategory blogCategory) {
        List<BlogCategory> list = blogCategoryService.findByKeyword(blogCategory);
        return Result.ok(list);
    }

    @RequestMapping(value = "/fuzzySelect", method = RequestMethod.POST)
    public Result fuzzySelect(@RequestBody BlogCategory blogCategory,
                              @RequestParam("currentPage") Integer currentPage,
                              @RequestParam("pageSize") Integer pageSize) {

        if (blogCategory == null) {
            blogCategory = new BlogCategory();
        }

        currentPage = initCurrentPage(currentPage);
        pageSize = initPageSize(pageSize);

        PagedResult<BlogCategory> pagedResult = blogCategoryService.fuzzySelect(blogCategory, currentPage, pageSize);

        return Result.ok(pagedResult);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody BlogCategory blogCategory) {
        if (blogCategory == null || !StringUtils.hasLength(blogCategory.getName())) {
            return Result.errorMsg("分类名称不能为空！");
        }
        return blogCategoryService.save(blogCategory);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateById(@RequestBody BlogCategory blogCategory) {
        if (blogCategory == null || blogCategory.getId() <= 0) {
            return Result.errorMsg("分类Id不能为空！");
        }
        return blogCategoryService.updateById(blogCategory);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestBody BlogCategory blogCategory) {
        if (blogCategory == null || blogCategory.getId() <= 0) {
            return Result.errorMsg("分类Id不能为空！");
        }
        return blogCategoryService.delete(blogCategory);
    }
}
