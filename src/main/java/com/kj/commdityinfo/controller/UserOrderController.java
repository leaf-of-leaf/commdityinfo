package com.kj.commdityinfo.controller;

import com.kj.commdityinfo.bean.OrderItem;
import com.kj.commdityinfo.bean.UserOrder;
import com.kj.commdityinfo.service.UserOrderService;
import com.kj.commdityinfo.utils.Message;
import com.kj.commdityinfo.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *订单管理
 * @author kj
 * @Date 2020/6/12 21:08
 * @Version 1.0
 */
@RestController
public class UserOrderController {

    @Autowired
    private UserOrderService userOrderService;

    @GetMapping("/findUserOrderByOrderId")
    public Message<Object> findUserOrderByOrderId(Integer orderId){
        try{
            return MessageUtil.success(userOrderService.findUserOrderByOrderId(orderId));
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }

    @GetMapping("/findUserOrderByOrderNum")
    public Message<Object> findUserOrderByOrderNum(String orderNum){
        try{
            return MessageUtil.success(userOrderService.findUserOrderByOrderNum(orderNum));
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }

    @GetMapping("/findUserOrderByUserId")
    public Message<Object> findUserOrderByUserId(Integer userId){
        try{
            return MessageUtil.success(userOrderService.findUserOrderByUserId(userId));
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }

    @GetMapping("/deleteUserOrderByUserId")
    public Message<Object> deleteUserOrderByUserId(Integer userId){
        try{
            userOrderService.deleteUserOrderByUserId(userId);
            return MessageUtil.success("成功");
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }

    @GetMapping("/deleteUserOrderByOrderNum")
    public Message<Object> deleteUserOrderByOrderNum(String orderNum){
        try{
            userOrderService.deleteUserOrderByOrderNum(orderNum);
            return MessageUtil.success("成功");
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }


    @PostMapping("/insertUserOrder")
    public Message<Object> insertUserOrder(UserOrder userOrder){
        try{
            userOrderService.insertUserOrder(userOrder);
            return MessageUtil.success("成功");
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }
}
