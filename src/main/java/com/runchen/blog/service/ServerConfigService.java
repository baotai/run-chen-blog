package com.runchen.blog.service;

import com.runchen.blog.common.PagedResult;
import com.runchen.blog.common.Result;
import com.runchen.blog.entity.po.ServerConfig;
import com.runchen.blog.mapper.ServerConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ServerConfigService extends BaseService<ServerConfig, ServerConfigMapper> {

    @Autowired
    private ServerConfigMapper serverConfigMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public ServerConfig findById(Long id) {
        Map<String, Object> map = idToMap(id);
        return findOne(map, serverConfigMapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ServerConfig> findByKeyword(ServerConfig entity) {
        Map<String, Object> map = entity.parseToMap();

        return findByKeyword(map, serverConfigMapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedResult<ServerConfig> fuzzySelect(ServerConfig entity, Integer currentPage, Integer pageSize) {

        Map<String, Object> map = entity.parseToMap();
        List<ServerConfig> list = fuzzySelect(map, currentPage, pageSize, serverConfigMapper);

        PagedResult<ServerConfig> pagedResult = new PagedResult<>();
        if (list == null || list.size() == 0) {
            return pagedResult;
        }

        //总记录数
        Integer records = countRows(map, serverConfigMapper);
        //总页数
        Integer total = countTotal(records, pageSize);

        return pagedResult
                .setPage(currentPage)
                .setTotal(total)
                .setRecords(records)
                .setRows(list);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result updateById(ServerConfig entity) {

        Map<String, Object> map = entity.only();
        Integer num = updateById(entity, map, serverConfigMapper);

        return result(num);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result save(ServerConfig entity) {

        Map<String, Object> map = entity.only();
        Integer num = save(entity, map, serverConfigMapper);

        return result(num);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result delete(ServerConfig entity) {
        entity.delete();
        serverConfigMapper.updateById(entity);
        return Result.ok();
    }
}
