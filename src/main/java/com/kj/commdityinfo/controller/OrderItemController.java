package com.kj.commdityinfo.controller;

import com.kj.commdityinfo.bean.OrderItem;
import com.kj.commdityinfo.service.OrderItemService;
import com.kj.commdityinfo.utils.Message;
import com.kj.commdityinfo.utils.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单项管理
 * @author kj
 * @Date 2020/6/12 14:07
 * @Version 1.0
 */
@Api(tags = "订单项管理")
@RestController
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;
    @ApiOperation(value = "通过订单号查询该订单下的所有订单项")
    @GetMapping("/findOrderItemByOrderNum")
    public Message<Object> findOrderItemByOrderNum(String orderNum){
        try{
            return MessageUtil.success(orderItemService.findOrderItemByOrderNum(orderNum));
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }
    @ApiOperation(value = "通过用户id查询该用户下的所有订单项,包括已支付的和还在购物车的")
    @GetMapping("/findOrderItemByUserId")
    public Message<Object> findOrderItemByUserId(Integer userId){
        try{
            return MessageUtil.success(orderItemService.findOrderItemByUserId(userId));
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }
    @ApiOperation(value = "查询所有关于itemid的订单项")
    @GetMapping("/findOrderItemByItemId")
    public Message<Object> findOrderItemByItemId(Integer itemId){
        try{
            return MessageUtil.success(orderItemService.findOrderItemByItemId(itemId));
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }
    @ApiOperation(value = "通过orderNum查询userId用户下的,当orderNum为''或者'empty'时查询当前在购物车里的")
    @GetMapping("/findOrderItemByUserIdAndOrderNum")
    public Message<Object> findOrderItemByUserIdAndOrderNum(Integer userId, String orderNum){
        try{
            return MessageUtil.success(orderItemService.findOrderItemByUserIdAndOrderNum(userId, orderNum));
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }
    @ApiOperation(value = "通过订单项id删除订单项")
    @GetMapping("/deleteOrderItem")
    public Message<Object> deleteOrderItem(Integer orderItemId){
        try{
            orderItemService.deleteOrderItem(orderItemId);
            return MessageUtil.success("成功");
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }
    @ApiOperation(value = "删除该用户下的所有订单项")
    @GetMapping("/deleteOrderItemsByUserId")
    public Message<Object> deleteOrderItemsByUserId(Integer userId){
        try{
            orderItemService.deleteOrderItemsByUserId(userId);
            return MessageUtil.success("成功");
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }
    @ApiOperation(value = "删除该订单下的所有订单项")
    @GetMapping("/deleteOrderItemsByUserOrderId")
    public Message<Object> deleteOrderItemsByUserOrderId(Integer orderItemId){
        try{
            orderItemService.deleteOrderItemsByUserOrderId(orderItemId);
            return MessageUtil.success("成功");
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }
    @ApiOperation(value = "添加订单项，即加入购物车，已包含不能重复添加的功能")
    @PostMapping("/insertOrderItem")
    public Message<Object> insertOrderItem(OrderItem orderItem){
        try{
            orderItemService.insertOrderItem(orderItem);
            return MessageUtil.success("成功");
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }
}
