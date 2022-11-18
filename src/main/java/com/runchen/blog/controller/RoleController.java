package com.runchen.blog.controller;

import com.runchen.blog.common.PagedResult;
import com.runchen.blog.common.Result;
import com.runchen.blog.controller.member.BaseController;
import com.runchen.blog.entity.po.Roles;
import com.runchen.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Result findById(@RequestParam("id") Long id) {

        if (id <= 0) {
            return Result.errorMsg("id不能为空！");
        }

        Roles entity = roleService.findById(id);

        return Result.ok(entity);
    }

    @RequestMapping(value = "/findList", method = RequestMethod.POST)
    public Result findList(@RequestBody Roles roles) {
        List<Roles> list = roleService.findByKeyword(roles);
        return Result.ok(list);
    }

    @RequestMapping(value = "/fuzzySelect", method = RequestMethod.POST)
    public Result fuzzySelect(@RequestBody Roles roles,
                              @RequestParam("currentPage") Integer currentPage,
                              @RequestParam("pageSize") Integer pageSize) {

        if (roles == null) {
            roles = new Roles();
        }

        currentPage = initCurrentPage(currentPage);
        pageSize = initPageSize(pageSize);

        PagedResult<Roles> pagedResult = roleService.fuzzySelect(roles, currentPage, pageSize);

        return Result.ok(pagedResult);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody Roles roles) {
        if (roles == null || !StringUtils.hasLength(roles.getName())) {
            return Result.errorMsg("角色名称不能为空！");
        }
        return roleService.save(roles);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateById(@RequestBody Roles roles) {
        if (roles == null || roles.getId() <= 0) {
            return Result.errorMsg("Id不能为空！");
        }
        return roleService.updateById(roles);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestBody Roles roles) {
        if (roles == null || roles.getId() <= 0) {
            return Result.errorMsg("Id不能为空！");
        }
        return roleService.delete(roles);
    }
}
