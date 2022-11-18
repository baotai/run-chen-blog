package com.runchen.blog.controller.member;

import com.runchen.blog.common.PagedResult;
import com.runchen.blog.common.Result;
import com.runchen.blog.entity.po.BlogComment;
import com.runchen.blog.service.BlogCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/run_chen/blog/comment")
public class BlogCommentController extends BaseController {

    @Autowired
    private BlogCommentService blogCommentService;

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Result findById(@RequestParam("id") Long id) {

        if (id <= 0) {
            return Result.errorMsg("id不能为空！");
        }

        BlogComment entity = blogCommentService.findById(id);

        return Result.ok(entity);
    }

    @RequestMapping(value = "/findList", method = RequestMethod.POST)
    public Result findList(@RequestBody BlogComment blogComment) {
        List<BlogComment> list = blogCommentService.findByKeyword(blogComment);
        return Result.ok(list);
    }

    @RequestMapping(value = "/fuzzySelect", method = RequestMethod.POST)
    public Result fuzzySelect(@RequestBody BlogComment blogComment,
                              @RequestParam("currentPage") Integer currentPage,
                              @RequestParam("pageSize") Integer pageSize) {

        if (blogComment == null) {
            blogComment = new BlogComment();
        }

        currentPage = initCurrentPage(currentPage);
        pageSize = initPageSize(pageSize);

        PagedResult<BlogComment> pagedResult = blogCommentService.fuzzySelect(blogComment, currentPage, pageSize);

        return Result.ok(pagedResult);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody BlogComment blogComment) {
        if (blogComment == null) {
            return Result.errorMsg("不能为空！");
        }
        Long blogId = blogComment.getBlogId();
        if (blogId == null || blogId < 1) {
            return Result.errorMsg("博文id不能为空！");
        }
        String content = blogComment.getContent();
        if (!StringUtils.hasLength(content)) {
            return Result.errorMsg("评论内容不能为空！");
        }
        return blogCommentService.save(blogComment);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateById(@RequestBody BlogComment blogComment) {
        if (blogComment == null || blogComment.getId() < 1) {
            return Result.errorMsg("Id不能为空！");
        }
        return blogCommentService.updateById(blogComment);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestBody BlogComment blogComment) {
        if (blogComment == null || blogComment.getId() < 1) {
            return Result.errorMsg("Id不能为空！");
        }
        return blogCommentService.delete(blogComment);
    }
}
