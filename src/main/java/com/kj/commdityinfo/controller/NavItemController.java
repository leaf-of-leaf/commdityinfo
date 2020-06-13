package com.kj.commdityinfo.controller;

import com.kj.commdityinfo.service.NavItemService;
import com.kj.commdityinfo.utils.Message;
import com.kj.commdityinfo.utils.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kj
 * @Date 2020/6/13 14:56
 * @Version 1.0
 */
@Api(tags = "一级栏目管理")
@RestController
public class NavItemController {

    @Autowired
    private NavItemService navItemService;

    @ApiOperation(value = "查询所有的一级栏目")
    @GetMapping("/findAllNavItem")
    public Message<Object> findAllNavItem(){
        try{
            return MessageUtil.success(navItemService.findAllNavItem());
        }catch (Exception e){
            return MessageUtil.error(400,e.getMessage());
        }
    }

    @ApiOperation(value = "通过id查询一级栏目")
    @GetMapping("/findNavItemByNavItemId")
    public Message<Object> findNavItemByNavItemId(Integer navItemId){
        try{
            return MessageUtil.success(navItemService.findNavItemByNavItemId(navItemId));
        }catch (Exception e){
            return MessageUtil.error(400,e.getMessage());
        }
    }

    @ApiOperation(value = "通过Name查询一级栏目")
    @GetMapping("/findNavItemByNavItemName")
    public Message<Object> findNavItemByNavItemName(String navItemName){
        try{
            return MessageUtil.success(navItemService.findNavItemByNavItemName(navItemName));
        }catch (Exception e){
            return MessageUtil.error(400,e.getMessage());
        }
    }

}
