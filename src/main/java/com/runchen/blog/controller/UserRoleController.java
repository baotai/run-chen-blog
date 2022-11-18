package com.runchen.blog.controller;

import com.runchen.blog.common.PagedResult;
import com.runchen.blog.common.Result;
import com.runchen.blog.controller.member.BaseController;
import com.runchen.blog.entity.po.UserRole;
import com.runchen.blog.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/role")
public class UserRoleController extends BaseController {

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Result findById(@RequestParam("id") Long id) {

        if (id <= 0) {
            return Result.errorMsg("id不能为空！");
        }

        UserRole entity = userRoleService.findById(id);

        return Result.ok(entity);
    }

    @RequestMapping(value = "/findList", method = RequestMethod.POST)
    public Result findList(@RequestBody UserRole userRole) {
        List<UserRole> list = userRoleService.findByKeyword(userRole);
        return Result.ok(list);
    }

    @RequestMapping(value = "/fuzzySelect", method = RequestMethod.POST)
    public Result fuzzySelect(@RequestBody UserRole userRole,
                              @RequestParam("currentPage") Integer currentPage,
                              @RequestParam("pageSize") Integer pageSize) {

        if (userRole == null) {
            userRole = new UserRole();
        }

        currentPage = initCurrentPage(currentPage);
        pageSize = initPageSize(pageSize);

        PagedResult<UserRole> pagedResult = userRoleService.fuzzySelect(userRole, currentPage, pageSize);

        return Result.ok(pagedResult);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody UserRole userRole) {
        if (userRole == null) {
            return Result.errorMsg("不能为空！");
        }
        Long userId = userRole.getUserId();
        if (userId == null || userId <= 0) {
            return Result.errorMsg("请选择用户！");
        }
        Long roleId = userRole.getRoleId();
        if (roleId == null || roleId <= 0) {
            return Result.errorMsg("请选择角色！");
        }
        return userRoleService.save(userRole);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateById(@RequestBody UserRole userRole) {
        if (userRole == null || userRole.getId() <= 0) {
            return Result.errorMsg("Id不能为空！");
        }
        return userRoleService.updateById(userRole);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestBody UserRole userRole) {
        if (userRole == null || userRole.getId() <= 0) {
            return Result.errorMsg("Id不能为空！");
        }
        return userRoleService.delete(userRole);
    }
}
