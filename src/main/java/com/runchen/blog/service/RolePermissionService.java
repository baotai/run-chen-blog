package com.runchen.blog.service;

import com.runchen.blog.common.PagedResult;
import com.runchen.blog.common.Result;
import com.runchen.blog.entity.po.RolePermission;
import com.runchen.blog.mapper.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class RolePermissionService extends BaseService<RolePermission, RolePermissionMapper> {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public RolePermission findById(Long id) {
        Map<String, Object> map = idToMap(id);
        return findOne(map, rolePermissionMapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RolePermission> findByKeyword(RolePermission entity) {
        Map<String, Object> map = entity.parseToMap();

        return findByKeyword(map, rolePermissionMapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedResult<RolePermission> fuzzySelect(RolePermission entity, Integer currentPage, Integer pageSize) {

        Map<String, Object> map = entity.parseToMap();
        List<RolePermission> list = fuzzySelect(map, currentPage, pageSize, rolePermissionMapper);

        PagedResult<RolePermission> pagedResult = new PagedResult<>();
        if (list == null || list.size() == 0) {
            return pagedResult;
        }

        //总记录数
        Integer records = countRows(map, rolePermissionMapper);
        //总页数
        Integer total = countTotal(records, pageSize);

        return pagedResult
                .setPage(currentPage)
                .setTotal(total)
                .setRecords(records)
                .setRows(list);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result updateById(RolePermission entity) {

        Map<String, Object> map = entity.only();
        Integer num = updateById(entity, map, rolePermissionMapper);

        return result(num);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result save(RolePermission entity) {

        Map<String, Object> map = entity.only();
        Integer num = save(entity, map, rolePermissionMapper);

        return result(num);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result delete(RolePermission entity) {
        entity.delete();
        rolePermissionMapper.updateById(entity);
        return Result.ok();
    }
}
