package com.runchen.blog.service;

import com.runchen.blog.common.PagedResult;
import com.runchen.blog.common.Result;
import com.runchen.blog.entity.po.UserRole;
import com.runchen.blog.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class UserRoleService extends BaseService<UserRole, UserRoleMapper> {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public UserRole findById(Long id) {
        Map<String, Object> map = idToMap(id);
        return findOne(map, userRoleMapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<UserRole> findByKeyword(UserRole entity) {
        Map<String, Object> map = entity.parseToMap();

        return findByKeyword(map, userRoleMapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedResult<UserRole> fuzzySelect(UserRole entity, Integer currentPage, Integer pageSize) {
        PagedResult<UserRole> pagedResult = new PagedResult<>();

        Map<String, Object> map = entity.parseToMap();
        List<UserRole> list = fuzzySelect(map, currentPage, pageSize, userRoleMapper);

        if (list == null || list.size() == 0) {
            return pagedResult;
        }

        //总记录数
        Integer records = countRows(map, userRoleMapper);
        //总页数
        Integer total = countTotal(records, pageSize);

        return pagedResult
                .setPage(currentPage)
                .setTotal(total)
                .setRecords(records)
                .setRows(list);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result updateById(UserRole entity) {

        Map<String, Object> map = entity.only();
        Integer num = updateById(entity, map, userRoleMapper);

        return result(num);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result save(UserRole entity) {

        Map<String, Object> map = entity.only();
        Integer num = save(entity, map, userRoleMapper);

        return result(num);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result delete(UserRole entity) {
        entity.delete();
        userRoleMapper.updateById(entity);
        return Result.ok();
    }
}
