package com.runchen.blog.service;

import com.runchen.blog.common.PagedResult;
import com.runchen.blog.common.Result;
import com.runchen.blog.entity.po.BlogComment;
import com.runchen.blog.mapper.BlogCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class BlogCommentService extends BaseService<BlogComment, BlogCommentMapper> {

    @Autowired
    private BlogCommentMapper blogCommentMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public BlogComment findById(Long id) {
        Map<String, Object> map = idToMap(id);
        return findOne(map, blogCommentMapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<BlogComment> findByKeyword(BlogComment entity) {
        Map<String, Object> map = entity.parseToMap();

        return findByKeyword(map, blogCommentMapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedResult<BlogComment> fuzzySelect(BlogComment entity, Integer currentPage, Integer pageSize) {

        Map<String, Object> map = entity.parseToMap();
        List<BlogComment> list = fuzzySelect(map, currentPage, pageSize, blogCommentMapper);

        PagedResult<BlogComment> pagedResult = new PagedResult<>();
        if (list == null || list.size() == 0) {
            return pagedResult;
        }

        //总记录数
        Integer records = countRows(map, blogCommentMapper);
        //总页数
        Integer total = countTotal(records, pageSize);

        return pagedResult
                .setPage(currentPage)
                .setTotal(total)
                .setRecords(records)
                .setRows(list);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result updateById(BlogComment entity) {

        Integer num = updateById(entity, null, blogCommentMapper);

        return result(num);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result save(BlogComment entity) {

        Integer num = save(entity, null, blogCommentMapper);

        return result(num);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result delete(BlogComment entity) {
        entity.delete();
        blogCommentMapper.updateById(entity);
        return Result.ok();
    }
}
