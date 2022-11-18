package com.runchen.blog.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {

    /**
     * 查询单个实体信息
     */
    T findOne(@Param("paramsMap") Map<String, Object> map);

    /**
     * 查询特定条件实体群
     */
    List<T> findByKeyword(@Param("paramsMap") Map<String, Object> map);

    /**
     * 模糊查询
     */
    List<T> fuzzySelect(@Param("paramsMap") Map<String, Object> map);

    /**
     * 统计实体数量
     */
    Integer countRows(@Param("paramsMap") Map<String, Object> map);

    /**
     * 更新实体信息
     */
    Integer updateById(T entity);

    /**
     * 添加实体信息
     */
    Integer save(T entity);
}
