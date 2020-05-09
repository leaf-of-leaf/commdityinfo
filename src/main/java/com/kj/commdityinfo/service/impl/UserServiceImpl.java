package com.kj.commdityinfo.service.impl;

import com.kj.commdityinfo.bean.User;
import com.kj.commdityinfo.bean.UserExample;
import com.kj.commdityinfo.exception.UserException;
import com.kj.commdityinfo.mapper.UserMapper;
import com.kj.commdityinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author kj
 * @Date 2020/5/7 13:41
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public List<User> findAllUser() {
        UserExample example = new UserExample();
        List<User> users = userMapper.selectByExample(example);
        return users;
    }

    @Override
    public void deleteUserById(Integer userId) {
        if(userId == null){
            return;
        }
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public void saveUser(User user) throws Exception{
        Integer userId = user.getUserId();
        if(userId <= 0){
            throw new UserException("id不正确");
        }
        if(userId == null){
            userMapper.insertSelective(user);
        }
        if(userMapper.isExistsByUserId(userId) != null) {
            userMapper.updateByPrimaryKeySelective(user);
        }
    }

    @Override
    public User findUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User findUserByName(String name) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(name);
        List<User> users = userMapper.selectByExample(userExample);
        //name为unique,所以只会为1
        return users.get(0);
    }
}
