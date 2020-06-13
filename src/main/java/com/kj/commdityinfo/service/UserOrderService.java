package com.kj.commdityinfo.service;

import com.kj.commdityinfo.bean.UserOrder;

import java.util.List;

/**
 * @author kj
 * @Date 2020/6/12 13:52
 * @Version 1.0
 */
public interface UserOrderService {

    /**
     * 通过订单主键查询订单
     * @param orderId
     * @return
     */
    UserOrder findUserOrderByOrderId(Integer orderId);

    /**
     * 通过订单号(即也是支付宝支付时对应的订单号)查询订单
     * @param orderNum
     * @return
     */
    UserOrder findUserOrderByOrderNum(String orderNum);

    /**
     * 查询用户的所有订单
     * @param userId
     * @return
     */
    List<UserOrder> findUserOrderByUserId(Integer userId);

    /**
     * 删除该用户下的所有订单
     * @param userId
     */
    void deleteUserOrderByUserId(Integer userId);

    /**
     * 根据订单号删除订单
     * @param orderNum
     */
    void deleteUserOrderByOrderNum(String orderNum);

    /**
     * 插入
     * @param userOrder
     * @throws Exception
     */
    void insertUserOrder(UserOrder userOrder) throws Exception;

    /**
     * 修改订单状态
     * @param status
     */
    void updateUserOrderStatusByOrderNum(Integer status, String orderNum);
}
