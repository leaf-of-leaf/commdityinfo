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
     * @param id
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
    User findUserById(Integer userId);

    /**
     * 通过name查询User
     * @param userId
     * @return
     */
    User findUserByName(String name);

}
