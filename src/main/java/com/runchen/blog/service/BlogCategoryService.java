package com.runchen.blog.service;

import com.runchen.blog.common.PagedResult;
import com.runchen.blog.common.Result;
import com.runchen.blog.entity.po.BlogCategory;
import com.runchen.blog.mapper.BlogCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class BlogCategoryService extends BaseService<BlogCategory, BlogCategoryMapper> {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public BlogCategory findById(Long id) {
        Map<String, Object> map = idToMap(id);
        return findOne(map, blogCategoryMapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<BlogCategory> findByKeyword(BlogCategory entity) {
        Map<String, Object> map = entity.parseToMap();

        return findByKeyword(map, blogCategoryMapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedResult<BlogCategory> fuzzySelect(BlogCategory entity, Integer currentPage, Integer pageSize) {

        Map<String, Object> map = entity.parseToMap();
        List<BlogCategory> list = fuzzySelect(map, currentPage, pageSize, blogCategoryMapper);

        PagedResult<BlogCategory> pagedResult = new PagedResult<>();
        if (list == null || list.size() == 0) {
            return pagedResult;
        }

        //总记录数
        Integer records = countRows(map, blogCategoryMapper);
        //总页数
        Integer total = countTotal(records, pageSize);

        return pagedResult
                .setPage(currentPage)
                .setTotal(total)
                .setRecords(records)
                .setRows(list);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result updateById(BlogCategory entity) {

        Map<String, Object> map = entity.only();
        Integer num = updateById(entity, map, blogCategoryMapper);

        return result(num);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result save(BlogCategory entity) {

        Map<String, Object> map = entity.only();
        Integer num = save(entity, map, blogCategoryMapper);

        return result(num);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result delete(BlogCategory entity) {
        entity.delete();
        blogCategoryMapper.updateById(entity);
        return Result.ok();
    }
}
