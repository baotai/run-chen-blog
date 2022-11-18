package com.runchen.blog.controller.member;

import com.runchen.blog.common.PagedResult;
import com.runchen.blog.common.Result;
import com.runchen.blog.entity.po.BlogCategory;
import com.runchen.blog.entity.po.RcLink;
import com.runchen.blog.service.RcLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/run_chen/link")
public class RcLinkController extends BaseController {

    @Autowired
    private RcLinkService rcLinkService;

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Result findById(@RequestParam("id") Long id) {

        if (id <= 0) {
            return Result.errorMsg("id不能为空！");
        }

        RcLink entity = rcLinkService.findById(id);

        return Result.ok(entity);
    }

    @RequestMapping(value = "/findList", method = RequestMethod.POST)
    public Result findList(@RequestBody RcLink rcLink) {
        List<RcLink> list = rcLinkService.findByKeyword(rcLink);
        return Result.ok(list);
    }

    @RequestMapping(value = "/fuzzySelect", method = RequestMethod.POST)
    public Result fuzzySelect(@RequestBody RcLink rcLink,
                              @RequestParam("currentPage") Integer currentPage,
                              @RequestParam("pageSize") Integer pageSize) {

        if (rcLink == null) {
            rcLink = new RcLink();
        }

        currentPage = initCurrentPage(currentPage);
        pageSize = initPageSize(pageSize);

        PagedResult<RcLink> pagedResult = rcLinkService.fuzzySelect(rcLink, currentPage, pageSize);

        return Result.ok(pagedResult);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody RcLink rcLink) {
        if (rcLink == null) {
            return Result.errorMsg("不能为空！");
        }
        if (!StringUtils.hasLength(rcLink.getName())) {
            return Result.errorMsg("网站名称不能为空！");
        }
        if (!StringUtils.hasLength(rcLink.getType())) {
            return Result.errorMsg("友链类别 不能为空！");
        }
        if (!StringUtils.hasLength(rcLink.getUrl())) {
            return Result.errorMsg("网站链接不能为空！");
        }
        return rcLinkService.save(rcLink);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateById(@RequestBody RcLink rcLink) {
        if (rcLink == null || rcLink.getId() <= 0) {
            return Result.errorMsg("分类Id不能为空！");
        }
        return rcLinkService.updateById(rcLink);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestBody RcLink rcLink) {
        if (rcLink == null || rcLink.getId() <= 0) {
            return Result.errorMsg("分类Id不能为空！");
        }
        return rcLinkService.delete(rcLink);
    }
}
