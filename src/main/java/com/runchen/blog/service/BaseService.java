package com.runchen.blog.service;

import com.runchen.blog.common.Result;
import com.runchen.blog.entity.po.BasePO;
import com.runchen.blog.mapper.BaseMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseService<E extends BasePO, M extends BaseMapper<E>> {

    @Transactional(propagation = Propagation.SUPPORTS)
    public E findOne(Map<String, Object> map, M mapper) {
        return mapper.findOne(map);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<E> findByKeyword(Map<String, Object> map, M mapper) {
        return mapper.findByKeyword(map);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<E> fuzzySelect(Map<String, Object> map,Integer currentPage, Integer pageSize, M mapper) {

        Integer startNum = (currentPage - 1) * pageSize;
        map.put("startNum", startNum);
        map.put("pageSize", pageSize);

        return mapper.fuzzySelect(map);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer countRows(Map<String, Object> map, M mapper) {
        return mapper.countRows(map);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateById(E entity, Map<String, Object> map, M mapper) {

        Long id = entity.getId();
        if (map != null && map.size() > 0 && isExists(map, id, mapper)) {
            return -1;
        }

        entity.update();
        Integer result = mapper.updateById(entity);
        return result == 1 ? 1 : 0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(E entity, Map<String, Object> map, M mapper) {

        //查重
        if (map != null && map.size() > 0 && isExists(map, null, mapper)) {
            return -1;
        }

        entity.save();
        Integer result = mapper.save(entity);
        return result == 1 ? 1 : 0;
    }

    /**
     * 新增 或者 修改时判断唯一字段不能重复
     *
     * @param map    唯一字段集： 如：{"username":"123456"}
     * @param id     主键， 用于识别是新增还是修改
     * @param mapper mapper 实例
     * @return 结果： true-存在，false-不存在
     */
    private boolean isExists(Map<String, Object> map, Long id, M mapper) {
        List<E> list = findByKeyword(map, mapper);

        //not exists
        if (list == null || list.size() <= 0) {
            return false;
        }

        //already exists when insert
        if (id == null || id == 0) {
            return true;
        }

        for (E element : list) {
            //already exists when update
            if (!element.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static Result result(Integer num) {
        Result result;
        switch (num) {
            case -1:
                result = Result.errorMsg("已存在！");
                break;
            case 0:
                result = Result.errorMsg("更新失败！");
                break;
            default:
                result = Result.ok();
                break;
        }
        return result;
    }

    /**
     * 计算总页数
     *
     * @param records  总记录数
     * @param pageSize 每页大小
     * @return 总页数
     */
    public static Integer countTotal(Integer records, Integer pageSize) {
        return (records / pageSize) + ((records % pageSize) != 0 ? 1 : 0);
    }

    public static Map<String, Object> idToMap(Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return map;
    }
}
