package com.kj.commdityinfo.service;

import com.kj.commdityinfo.bean.User;

import java.util.List;

/**
 * @author kj
 * @Date 2020/5/7 13:41
 * @Version 1.0
 */
public interface UserService {
    /**
     * 查询所有User
     * @return
     */
    List<User> findAllUser();

    /**
     * 通过id删除User
     * @param userId
     */

    void deleteUserById(Integer userId);

    /**
     * 保存User
     * @param user
     */
    void saveUser(User user) throws Exception;

    /**
     * 通过id查询User
     * @param userId
     * @return
     */
    User findUserById(Integer userId) throws Exception;

    /**
     * 通过name查询User
     * @param name
     * @return
     */
    User findUserByName(String name) throws Exception;

    /**
     * 通过mobile查询User
     * @param mobile
     * @return
     */
    User findUserAndRoleByMobile(String mobile);


    /**
     * 获取当前登录的用户信息
     * @return
     * @throws Exception
     */
    User getUserInfo() throws Exception;
}
