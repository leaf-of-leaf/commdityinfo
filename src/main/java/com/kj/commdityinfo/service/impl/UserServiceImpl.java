package com.kj.commdityinfo.service.impl;

import com.kj.commdityinfo.bean.User;
import com.kj.commdityinfo.bean.UserExample;
import com.kj.commdityinfo.exception.SystemException;
import com.kj.commdityinfo.mapper.UserMapper;
import com.kj.commdityinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
            throw new SystemException("userId不正确");
        }
        if(userId == null){
            user.setRoleId(5);
            userMapper.insertSelective(user);
        }
        if(userMapper.isExistsByUserId(userId) != null) {
            userMapper.updateByPrimaryKeySelective(user);
        }
    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        if(userId == null){
            throw new SystemException("userId为空");
        }
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User findUserByName(String name) throws Exception {
        if(StringUtils.isEmpty(name)){
            throw new SystemException("name为空");
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(name);
        List<User> users = userMapper.selectByExample(userExample);
        //name为unique,所以只会为1
        return users.get(0);
    }


    @Override
    public User findUserAndRoleByMobile(String mobile) {
        return userMapper.findUserAndRoleByMobile(mobile);
    }

    @Override
    public User getUserInfo() throws Exception {
//       this.findUserByName()
        return null;
    }

}
