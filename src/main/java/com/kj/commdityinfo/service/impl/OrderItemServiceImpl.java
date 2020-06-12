package com.kj.commdityinfo.service.impl;

import com.alipay.api.domain.OrderItem;
import com.kj.commdityinfo.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kj
 * @Date 2020/6/12 14:06
 * @Version 1.0
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

//    @Autowired(required = false)

    @Override
    public List<OrderItem> findOrderItemByOrderId(Integer orderId) {
        return null;
    }

    @Override
    public List<OrderItem> findOrderItemByUserId(Integer userId) {
        return null;
    }

    @Override
    public List<OrderItem> findOrderItemByItemId(Integer itemId) {
        return null;
    }

    @Override
    public void deleteOrderItem(Integer orderItemId) {

    }

    @Override
    public void deleteOrderItemsByUserId(Integer userId) {

    }

    @Override
    public void deleteOrderItemsByUserOrderId(Integer orderItemId) {

    }

    @Override
    public void insertOrderItem(OrderItem orderItem) {

    }
}
