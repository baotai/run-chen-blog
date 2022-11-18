package com.runchen.blog.service;

import com.runchen.blog.common.PagedResult;
import com.runchen.blog.common.Result;
import com.runchen.blog.entity.po.Users;
import com.runchen.blog.mapper.UsersMapper;
import com.runchen.blog.util.MD5Utils;
import com.runchen.blog.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService extends BaseService<Users, UsersMapper> {

    @Autowired
    private UsersMapper usersMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public Users findById(Long id) {
        Map<String, Object> map = idToMap(id);
        Users users = findOne(map, usersMapper);
        return users.mask();
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public Users findByUsername(String username) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        Users users = findOne(map, usersMapper);
        return users.mask();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Users> findByKeyword(Users entity) {
        Map<String, Object> map = entity.parseToMap();
        List<Users> list = findByKeyword(map, usersMapper);
        list.forEach(Users::mask);
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedResult<Users> fuzzySelect(Users entity, Integer currentPage, Integer pageSize) {
        PagedResult<Users> pagedResult = new PagedResult<>();

        Map<String, Object> map = entity.parseToMap();
        List<Users> list = fuzzySelect(map, currentPage, pageSize, usersMapper);

        if (list == null || list.size() == 0) {
            return pagedResult;
        }

        list.forEach(Users::mask);
        //总记录数
        Integer records = countRows(map, usersMapper);
        //总页数
        Integer total = countTotal(records, pageSize);

        return pagedResult
                .setPage(currentPage)
                .setTotal(total)
                .setRecords(records)
                .setRows(list);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result updateById(Users entity) {

        //修改密码
        String password = entity.getPassword();
        if (StringUtils.hasLength(password)) {
            String salt = RandomUtil.randomStr(password.length());
            String pwd = MD5Utils.encode(password, salt);
            entity.setSalt(salt).setPassword(pwd);
        }

        Map<String, Object> map = entity.only();
        Integer num = updateById(entity, map, usersMapper);

        return result(num);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result save(Users entity) {

        String password = entity.getPassword();
        String salt = RandomUtil.randomStr(password.length());
        String pwd = MD5Utils.encode(password, salt);

        entity.setSalt(salt).setPassword(pwd);

        Map<String, Object> map = entity.only();
        Integer num = save(entity, map, usersMapper);

        return result(num);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result delete(Users entity) {
        entity.delete();
        usersMapper.updateById(entity);
        return Result.ok();
    }
}
