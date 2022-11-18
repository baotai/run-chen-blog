package com.runchen.blog.controller.member;

import com.runchen.blog.common.PagedResult;
import com.runchen.blog.common.Result;
import com.runchen.blog.entity.po.Users;
import com.runchen.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/run_chen/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Result findById(@RequestParam("id") Long id) {

        if (id <= 0) {
            return Result.errorMsg("id不能为空！");
        }

        Users entity = userService.findById(id);

        return Result.ok(entity);
    }

    @RequestMapping(value = "/findList", method = RequestMethod.POST)
    public Result findList(@RequestBody Users users) {
        List<Users> list = userService.findByKeyword(users);
        return Result.ok(list);
    }

    @RequestMapping(value = "/fuzzySelect", method = RequestMethod.POST)
    public Result fuzzySelect(@RequestBody Users users,
                              @RequestParam("currentPage") Integer currentPage,
                              @RequestParam("pageSize") Integer pageSize) {

        if (users == null) {
            users = new Users();
        }

        currentPage = initCurrentPage(currentPage);
        pageSize = initPageSize(pageSize);

        PagedResult<Users> pagedResult = userService.fuzzySelect(users, currentPage, pageSize);

        return Result.ok(pagedResult);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody Users users) {
        if (users == null) {
            return Result.errorMsg("不能为空！");
        }
        if (!StringUtils.hasLength(users.getUsername())) {
            return Result.errorMsg("用户名不能为空！");
        }
        if (!StringUtils.hasLength(users.getPassword())) {
            return Result.errorMsg("密码不能为空！");
        }
        if (!StringUtils.hasLength(users.getNickName())) {
            users.setNickName(users.getUsername());
        }
        return userService.save(users);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateById(@RequestBody Users users) {
        if (users == null || users.getId() <= 0) {
            return Result.errorMsg("Id不能为空！");
        }
        return userService.updateById(users);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestBody Users users) {
        if (users == null || users.getId() <= 0) {
            return Result.errorMsg("Id不能为空！");
        }
        return userService.delete(users);
    }
}
