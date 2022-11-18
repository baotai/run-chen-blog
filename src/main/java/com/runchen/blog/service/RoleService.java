package com.runchen.blog.service;

import com.runchen.blog.common.PagedResult;
import com.runchen.blog.common.Result;
import com.runchen.blog.entity.po.Roles;
import com.runchen.blog.mapper.RolesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class RoleService extends BaseService<Roles, RolesMapper> {

    @Autowired
    private RolesMapper rolesMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public Roles findById(Long id) {
        Map<String, Object> map = idToMap(id);
        return findOne(map, rolesMapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Roles> findByKeyword(Roles entity) {
        Map<String, Object> map = entity.parseToMap();

        return findByKeyword(map, rolesMapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedResult<Roles> fuzzySelect(Roles entity, Integer currentPage, Integer pageSize) {

        Map<String, Object> map = entity.parseToMap();
        List<Roles> list = fuzzySelect(map, currentPage, pageSize, rolesMapper);

        PagedResult<Roles> pagedResult = new PagedResult<>();
        if (list == null || list.size() == 0) {
            return pagedResult;
        }

        //总记录数
        Integer records = countRows(map, rolesMapper);
        //总页数
        Integer total = countTotal(records, pageSize);

        return pagedResult
                .setPage(currentPage)
                .setTotal(total)
                .setRecords(records)
                .setRows(list);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result updateById(Roles entity) {

        Map<String, Object> map = entity.only();
        Integer num = updateById(entity, map, rolesMapper);

        return result(num);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result save(Roles entity) {

        Map<String, Object> map = entity.only();
        Integer num = save(entity, map, rolesMapper);

        return result(num);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result delete(Roles entity) {
        entity.delete();
        rolesMapper.updateById(entity);
        return Result.ok();
    }
}
