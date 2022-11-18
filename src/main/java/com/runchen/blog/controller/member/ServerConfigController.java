package com.runchen.blog.controller.member;

import com.runchen.blog.common.PagedResult;
import com.runchen.blog.common.Result;
import com.runchen.blog.entity.po.ServerConfig;
import com.runchen.blog.service.ServerConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/run_chen/server/config")
public class ServerConfigController extends BaseController {

    @Autowired
    private ServerConfigService serverConfigService;

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Result findById(@RequestParam("id") Long id) {

        if (id <= 0) {
            return Result.errorMsg("id不能为空！");
        }

        ServerConfig entity = serverConfigService.findById(id);

        return Result.ok(entity);
    }

    @RequestMapping(value = "/findList", method = RequestMethod.POST)
    public Result findList(@RequestBody ServerConfig serverConfig) {
        List<ServerConfig> list = serverConfigService.findByKeyword(serverConfig);
        return Result.ok(list);
    }

    @RequestMapping(value = "/fuzzySelect", method = RequestMethod.POST)
    public Result fuzzySelect(@RequestBody ServerConfig serverConfig,
                              @RequestParam("currentPage") Integer currentPage,
                              @RequestParam("pageSize") Integer pageSize) {

        if (serverConfig == null) {
            serverConfig = new ServerConfig();
        }

        currentPage = initCurrentPage(currentPage);
        pageSize = initPageSize(pageSize);

        PagedResult<ServerConfig> pagedResult = serverConfigService.fuzzySelect(serverConfig, currentPage, pageSize);

        return Result.ok(pagedResult);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody ServerConfig serverConfig) {
        if (serverConfig == null) {
            return Result.errorMsg("不能为空！");
        }
        if (!StringUtils.hasLength(serverConfig.getKey())) {
            return Result.errorMsg("Key不能为空！");
        }
        if (!StringUtils.hasLength(serverConfig.getValue())) {
            return Result.errorMsg("value不能为空！");
        }
        return serverConfigService.save(serverConfig);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateById(@RequestBody ServerConfig serverConfig) {
        if (serverConfig == null || serverConfig.getId() <= 0) {
            return Result.errorMsg("Id不能为空！");
        }
        return serverConfigService.updateById(serverConfig);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestBody ServerConfig serverConfig) {
        if (serverConfig == null || serverConfig.getId() <= 0) {
            return Result.errorMsg("Id不能为空！");
        }
        return serverConfigService.delete(serverConfig);
    }
}
