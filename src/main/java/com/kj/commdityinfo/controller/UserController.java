package com.kj.commdityinfo.controller;

import com.kj.commdityinfo.bean.User;
import com.kj.commdityinfo.service.UserService;
import com.kj.commdityinfo.utils.Message;
import com.kj.commdityinfo.utils.MessageUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author kj
 * @Date 2020/5/7 13:50
 * @Version 1.0
 */
@Api
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findAllUser")
    public Message<List<User>> findAllUser(){
        return MessageUtil.success(userService.findAllUser());
    }

    @GetMapping("/deleteUser")
    public Message<String> deleteUser(Integer userId){
        userService.deleteUserById(userId);
        return MessageUtil.success("删除成功");
    }

    @PostMapping("/saveUser")
    public Message<String> saveUser(User user){
        Integer userId = user.getUserId();
        try{
            userService.saveUser(user);
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
        return MessageUtil.success(userId == null?"添加成功":"修改成功");
    }

    @GetMapping("/findUserById")
    public Message<User> findUserById(Integer userId){
        try{
            return MessageUtil.success(userService.findUserById(userId));
        } catch (Exception e){
            return MessageUtil.error(400,e.getMessage());
        }
    }

    @GetMapping("/findUserByName")
    public Message<User> findUserByName(String name){
        try{
            return MessageUtil.success(userService.findUserByName(name));
        } catch (Exception e){
            return MessageUtil.error(400,e.getMessage());
        }
    }
}
