package com.kj.commdityinfo.controller;

import com.kj.commdityinfo.bean.OrderItem;
import com.kj.commdityinfo.service.OrderItemService;
import com.kj.commdityinfo.utils.Message;
import com.kj.commdityinfo.utils.MessageUtil;
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
@RestController
public class OrderController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/findOrderItemByOrderNum")
    public Message<Object> findOrderItemByOrderNum(String orderNum){
        try{
            return MessageUtil.success(orderItemService.findOrderItemByOrderNum(orderNum));
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }

    @GetMapping("/findOrderItemByUserId")
    public Message<Object> findOrderItemByUserId(Integer userId){
        try{
            return MessageUtil.success(orderItemService.findOrderItemByUserId(userId));
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }

    @GetMapping("/findOrderItemByItemId")
    public Message<Object> findOrderItemByItemId(Integer itemId){
        try{
            return MessageUtil.success(orderItemService.findOrderItemByItemId(itemId));
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }

    @GetMapping("/deleteOrderItem")
    public Message<Object> deleteOrderItem(Integer orderItemId){
        try{
            orderItemService.deleteOrderItem(orderItemId);
            return MessageUtil.success("成功");
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }

    @GetMapping("/deleteOrderItemsByUserId")
    public Message<Object> deleteOrderItemsByUserId(Integer userId){
        try{
            orderItemService.deleteOrderItemsByUserId(userId);
            return MessageUtil.success("成功");
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }

    @GetMapping("/deleteOrderItemsByUserOrderId")
    public Message<Object> deleteOrderItemsByUserOrderId(Integer orderItemId){
        try{
            orderItemService.deleteOrderItemsByUserOrderId(orderItemId);
            return MessageUtil.success("成功");
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }

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
