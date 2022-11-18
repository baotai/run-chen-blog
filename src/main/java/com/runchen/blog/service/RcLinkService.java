package com.runchen.blog.service;

import com.runchen.blog.common.PagedResult;
import com.runchen.blog.common.Result;
import com.runchen.blog.entity.po.RcLink;
import com.runchen.blog.mapper.RcLinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class RcLinkService extends BaseService<RcLink, RcLinkMapper> {

    @Autowired
    private RcLinkMapper rcLinkMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public RcLink findById(Long id) {
        Map<String, Object> map = idToMap(id);
        return findOne(map, rcLinkMapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RcLink> findByKeyword(RcLink entity) {
        Map<String, Object> map = entity.parseToMap();

        return findByKeyword(map, rcLinkMapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedResult<RcLink> fuzzySelect(RcLink entity, Integer currentPage, Integer pageSize) {

        Map<String, Object> map = entity.parseToMap();
        List<RcLink> list = fuzzySelect(map, currentPage, pageSize, rcLinkMapper);

        PagedResult<RcLink> pagedResult = new PagedResult<>();
        if (list == null || list.size() == 0) {
            return pagedResult;
        }

        //总记录数
        Integer records = countRows(map, rcLinkMapper);
        //总页数
        Integer total = countTotal(records, pageSize);

        return pagedResult
                .setPage(currentPage)
                .setTotal(total)
                .setRecords(records)
                .setRows(list);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result updateById(RcLink entity) {

        Map<String, Object> map = entity.only();
        Integer num = updateById(entity, map, rcLinkMapper);

        return result(num);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result save(RcLink entity) {

        Map<String, Object> map = entity.only();
        Integer num = save(entity, map, rcLinkMapper);

        return result(num);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result delete(RcLink entity) {
        entity.delete();
        rcLinkMapper.updateById(entity);
        return Result.ok();
    }
}
