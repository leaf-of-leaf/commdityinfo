package com.kj.commdityinfo.service;

import com.alipay.api.domain.OrderItem;

import java.util.List;

/**
 * @author kj
 * @Date 2020/6/12 13:52
 * @Version 1.0
 */
public interface OrderItemService {

    /**
     * 通过订单号查询该订单下的所有订单项
     * @param orderId
     * @return
     */
    List<OrderItem> findOrderItemByOrderId(Integer orderId);

    /**
     * 通过用户id查询该用户下的所有订单项
     * @param userId
     * @return
     */
    List<OrderItem> findOrderItemByUserId(Integer userId);

    /**
     * 通过商品id查询所有订购了该商品的订单项
     * @param itemId
     * @return
     */
    List<OrderItem> findOrderItemByItemId(Integer itemId);

    /**
     * 通过订单项id删除订单项
     * @param orderItemId
     */
    void deleteOrderItem(Integer orderItemId);

    /**
     * 删除该用户下的所有订单项
     * @param userId
     */
    void deleteOrderItemsByUserId(Integer userId);

    /**
     * 删除该订单下的所有订单项
     * @param orderItemId
     */
    void deleteOrderItemsByUserOrderId(Integer orderItemId);


    /**
     * 添加订单项
     * @param orderItem
     */
    void insertOrderItem(OrderItem orderItem);
}
