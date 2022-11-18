package com.runchen.blog.service;

import com.runchen.blog.common.PagedResult;
import com.runchen.blog.common.Result;
import com.runchen.blog.entity.po.Permissions;
import com.runchen.blog.mapper.PermissionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class PermissionsService extends BaseService<Permissions, PermissionsMapper> {

    @Autowired
    private PermissionsMapper permissionsMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public Permissions findById(Long id) {
        Map<String, Object> map = idToMap(id);
        return findOne(map, permissionsMapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Permissions> findByKeyword(Permissions entity) {
        Map<String, Object> map = entity.parseToMap();

        return findByKeyword(map, permissionsMapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedResult<Permissions> fuzzySelect(Permissions entity, Integer currentPage, Integer pageSize) {

        Map<String, Object> map = entity.parseToMap();
        List<Permissions> list = fuzzySelect(map, currentPage, pageSize, permissionsMapper);

        PagedResult<Permissions> pagedResult = new PagedResult<>();
        if (list == null || list.size() == 0) {
            return pagedResult;
        }

        //总记录数
        Integer records = countRows(map, permissionsMapper);
        //总页数
        Integer total = countTotal(records, pageSize);

        return pagedResult
                .setPage(currentPage)
                .setTotal(total)
                .setRecords(records)
                .setRows(list);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result updateById(Permissions entity) {

        Map<String, Object> map = entity.only();

        Integer num = updateById(entity, map, permissionsMapper);

        return result(num);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result save(Permissions entity) {

        Map<String, Object> map = entity.only();

        Integer num = save(entity, map, permissionsMapper);

        return result(num);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result delete(Permissions entity) {
        entity.delete();
        permissionsMapper.updateById(entity);
        return Result.ok();
    }
}
