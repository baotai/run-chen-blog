package com.runchen.blog.service;

import com.runchen.blog.common.PagedResult;
import com.runchen.blog.common.Result;
import com.runchen.blog.entity.po.RcBlog;
import com.runchen.blog.mapper.RcBlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class RcBlogService extends BaseService<RcBlog, RcBlogMapper> {

    @Autowired
    private RcBlogMapper rcBlogMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public RcBlog findById(Long id) {
        Map<String, Object> map = idToMap(id);
        return findOne(map, rcBlogMapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RcBlog> findByKeyword(RcBlog entity) {
        Map<String, Object> map = entity.parseToMap();

        return findByKeyword(map, rcBlogMapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedResult<RcBlog> fuzzySelect(RcBlog entity, Integer currentPage, Integer pageSize) {

        Map<String, Object> map = entity.parseToMap();
        List<RcBlog> list = fuzzySelect(map, currentPage, pageSize, rcBlogMapper);

        PagedResult<RcBlog> pagedResult = new PagedResult<>();
        if (list == null || list.size() == 0) {
            return pagedResult;
        }

        //总记录数
        Integer records = countRows(map, rcBlogMapper);
        //总页数
        Integer total = countTotal(records, pageSize);

        return pagedResult
                .setPage(currentPage)
                .setTotal(total)
                .setRecords(records)
                .setRows(list);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result updateById(RcBlog entity) {

        Map<String, Object> map = entity.only();
        Integer num = updateById(entity, map, rcBlogMapper);

        return result(num);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result save(RcBlog entity) {

        Map<String, Object> map = entity.only();
        Integer num = save(entity, map, rcBlogMapper);

        return result(num);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result delete(RcBlog entity) {
        entity.delete();
        rcBlogMapper.updateById(entity);
        return Result.ok();
    }
}
