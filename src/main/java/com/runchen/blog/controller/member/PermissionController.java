package com.runchen.blog.controller.member;

import com.runchen.blog.common.PagedResult;
import com.runchen.blog.common.Result;
import com.runchen.blog.entity.po.Permissions;
import com.runchen.blog.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/run_chen/permission")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionsService permissionsService;

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Result findById(@RequestParam("id") Long id) {

        if (id <= 0) {
            return Result.errorMsg("id不能为空！");
        }

        Permissions entity = permissionsService.findById(id);

        return Result.ok(entity);
    }

    @RequestMapping(value = "/findList", method = RequestMethod.POST)
    public Result findList(@RequestBody Permissions permissions) {
        List<Permissions> list = permissionsService.findByKeyword(permissions);
        return Result.ok(list);
    }

    @RequestMapping(value = "/fuzzySelect", method = RequestMethod.POST)
    public Result fuzzySelect(@RequestBody Permissions permissions,
                              @RequestParam("currentPage") Integer currentPage,
                              @RequestParam("pageSize") Integer pageSize) {

        if (permissions == null) {
            permissions = new Permissions();
        }

        currentPage = initCurrentPage(currentPage);
        pageSize = initPageSize(pageSize);

        PagedResult<Permissions> pagedResult = permissionsService.fuzzySelect(permissions, currentPage, pageSize);

        return Result.ok(pagedResult);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody Permissions permissions) {
        if (permissions == null || !StringUtils.hasLength(permissions.getName())) {
            return Result.errorMsg("权限名称不能为空！");
        }
        return permissionsService.save(permissions);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateById(@RequestBody Permissions permissions) {
        if (permissions == null || permissions.getId() < 1) {
            return Result.errorMsg("分类Id不能为空！");
        }
        return permissionsService.updateById(permissions);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestBody Permissions permissions) {
        if (permissions == null || permissions.getId() < 1) {
            return Result.errorMsg("分类Id不能为空！");
        }
        return permissionsService.delete(permissions);
    }
}
