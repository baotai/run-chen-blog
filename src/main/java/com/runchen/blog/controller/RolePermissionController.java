package com.runchen.blog.controller;

import com.runchen.blog.common.PagedResult;
import com.runchen.blog.common.Result;
import com.runchen.blog.controller.member.BaseController;
import com.runchen.blog.entity.po.RolePermission;
import com.runchen.blog.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role/permission")
public class RolePermissionController extends BaseController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Result findById(@RequestParam("id") Long id) {

        if (id <= 0) {
            return Result.errorMsg("id不能为空！");
        }

        RolePermission entity = rolePermissionService.findById(id);

        return Result.ok(entity);
    }

    @RequestMapping(value = "/findList", method = RequestMethod.POST)
    public Result findList(@RequestBody RolePermission rolePermission) {
        List<RolePermission> list = rolePermissionService.findByKeyword(rolePermission);
        return Result.ok(list);
    }

    @RequestMapping(value = "/fuzzySelect", method = RequestMethod.POST)
    public Result fuzzySelect(@RequestBody RolePermission rolePermission,
                              @RequestParam("currentPage") Integer currentPage,
                              @RequestParam("pageSize") Integer pageSize) {

        if (rolePermission == null) {
            rolePermission = new RolePermission();
        }

        currentPage = initCurrentPage(currentPage);
        pageSize = initPageSize(pageSize);

        PagedResult<RolePermission> pagedResult = rolePermissionService.fuzzySelect(rolePermission, currentPage, pageSize);

        return Result.ok(pagedResult);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody RolePermission rolePermission) {
        if (rolePermission == null) {
            return Result.errorMsg("不能为空！");
        }
        Long roleId = rolePermission.getRoleId();
        if (roleId == null || roleId <= 0) {
            return Result.errorMsg("请选择角色");
        }
        Long permissionId = rolePermission.getPermissionId();
        if (permissionId == null || permissionId <= 0) {
            return Result.errorMsg("请选择角色！");
        }
        return rolePermissionService.save(rolePermission);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateById(@RequestBody RolePermission rolePermission) {
        if (rolePermission == null || rolePermission.getId() <= 0) {
            return Result.errorMsg("分类Id不能为空！");
        }
        return rolePermissionService.updateById(rolePermission);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestBody RolePermission rolePermission) {
        if (rolePermission == null || rolePermission.getId() <= 0) {
            return Result.errorMsg("Id不能为空！");
        }
        return rolePermissionService.delete(rolePermission);
    }
}
