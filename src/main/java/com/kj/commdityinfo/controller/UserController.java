package com.kj.commdityinfo.controller;

import com.kj.commdityinfo.bean.User;
import com.kj.commdityinfo.security.utils.JedisUtils;
import com.kj.commdityinfo.security.utils.JwtUtils;
import com.kj.commdityinfo.service.UserService;
import com.kj.commdityinfo.utils.Message;
import com.kj.commdityinfo.utils.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author kj
 * @Date 2020/5/7 13:50
 * @Version 1.0
 */
@Api(tags = "用户管理")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询所有User")
    @GetMapping("/findAllUser")
    public Message<List<User>> findAllUser(){
        return MessageUtil.success(userService.findAllUser());
    }

    @ApiOperation(value = "通过id删除User")
    @GetMapping("/deleteUser")
    public Message<String> deleteUser(Integer userId){
        userService.deleteUserById(userId);
        return MessageUtil.success("删除成功");
    }

    @ApiOperation(value = "保存User")
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

    @ApiOperation(value = "通过id查询User")
    @GetMapping("/findUserById")
    public Message<User> findUserById(Integer userId){
        try{
            return MessageUtil.success(userService.findUserById(userId));
        } catch (Exception e){
            return MessageUtil.error(400,e.getMessage());
        }
    }

    @ApiOperation(value = "通过name查询User")
    @GetMapping("/findUserByName")
    public Message<User> findUserByName(String name){
        try{
            return MessageUtil.success(userService.findUserByName(name));
        } catch (Exception e){
            return MessageUtil.error(400,e.getMessage());
        }
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/info")
    public Message<Object> info(HttpServletRequest request){
        try{
            String header = request.getHeader(JwtUtils.TOKEN_HEADER);
            String token = header.replaceAll(JwtUtils.TOKEN_PREFIX,"");
            return MessageUtil.success(userService.findUserByName(JwtUtils.getUsername(token)));
        } catch (Exception e){
            return MessageUtil.error(400,e.getMessage());
        }
    }

    @ApiOperation(value = "退出登录")
    @GetMapping("/logout")
    public Message<Object> logout(HttpServletRequest request){
        try{
            String header = request.getHeader(JwtUtils.TOKEN_HEADER);
            String token = header.replaceAll(JwtUtils.TOKEN_PREFIX,"");
            JedisUtils.del(JwtUtils.getUsername(token), 1);
            return MessageUtil.success("退出成功");
        } catch (Exception e){
            return MessageUtil.error(400,e.getMessage());
        }
    }
}
