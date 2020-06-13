package com.kj.commdityinfo.service.impl;

import com.kj.commdityinfo.bean.Item;
import com.kj.commdityinfo.bean.OrderItem;
import com.kj.commdityinfo.bean.OrderItemExample;
import com.kj.commdityinfo.bean.User;
import com.kj.commdityinfo.exception.SystemException;
import com.kj.commdityinfo.mapper.OrderItemMapper;
import com.kj.commdityinfo.service.ItemService;
import com.kj.commdityinfo.service.OrderItemService;
import com.kj.commdityinfo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author kj
 * @Date 2020/6/12 14:06
 * @Version 1.0
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired(required = false)
    private OrderItemMapper orderItemMapper;

    @Autowired(required = false)
    private ItemService itemService;

    @Autowired(required = false)
    private UserService userService;

    @Override
    public List<OrderItem> findOrderItemByOrderNum(String orderNum) {
        if(orderNum == null){
            throw new SystemException("参数有误");
        }

        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.createCriteria().andOrderNumEqualTo(orderNum);

        return orderItemMapper.selectByExample(orderItemExample);
    }

    @Override
    public List<OrderItem> findOrderItemByUserId(Integer userId) {
        if(userId == null){
            throw new SystemException("参数有误");
        }

        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.createCriteria().andUserIdEqualTo(userId);

        return orderItemMapper.selectByExample(orderItemExample);
    }

    @Override
    public List<OrderItem> findOrderItemByItemId(Integer itemId) {
        if(itemId == null){
            throw new SystemException("参数有误");
        }

        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.createCriteria().andItemIdEqualTo(itemId);

        return orderItemMapper.selectByExample(orderItemExample);
    }

    @Override
    public void deleteOrderItem(Integer orderItemId) {
        orderItemMapper.deleteByPrimaryKey(orderItemId);
    }

    @Override
    public void deleteOrderItemsByUserId(Integer userId) {
        if(userId == null){
            throw new SystemException("参数有误");
        }

        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.createCriteria().andUserIdEqualTo(userId);

        orderItemMapper.deleteByExample(orderItemExample);
    }

    @Override
    public void deleteOrderItemsByUserOrderId(Integer orderItemId) {
        if(orderItemId == null){
            throw new SystemException("参数有误");
        }

        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.createCriteria().andUserIdEqualTo(orderItemId);



        orderItemMapper.deleteByExample(orderItemExample);
    }

    @Override
    public void insertOrderItem(OrderItem orderItem) throws Exception {
        if(orderItem == null){
            throw new SystemException("参数有误");
        }

        //查询是否存在重复点击加入购物车的情况,即不存在同一用户，同一产品，但没有对应订单号的
        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.createCriteria().andUserIdEqualTo(orderItem.getUserId())
                .andItemIdEqualTo(orderItem.getItemId())
                .andOrderNumEqualTo("");

        List<OrderItem> orderItems = orderItemMapper.selectByExample(orderItemExample);
        if(orderItems.size() != 0){
            throw new SystemException("购物车中已存在对应商品");
        }

        Item itemByItemId = itemService.findItemByItemId(orderItem.getItemId());
        if(itemByItemId == null){
            throw new SystemException("商品出错");
        }
        //暂不，在订单生成时设置设置orderNum,为当前时间戳+用户id+商品id
        //orderItem.setOrderNum(System.currentTimeMillis() + orderItem.getUserId() + orderItem.getItemId() + "");

        orderItem.setPrice(itemByItemId.getPrice());

        orderItem.setPaid(itemByItemId.getPrice() * orderItem.getBuyCounts());

        orderItem.setOrderNum("");

        orderItemMapper.insertSelective(orderItem);
    }

    @Override
    public void updateOrderItems(Integer[] ids, String orderNum) {
        if(ids == null || ids.length == 0 || StringUtils.isBlank(orderNum)){
            throw new SystemException("参数错误");
        }
        orderItemMapper.updateOrderItems(ids, orderNum);
    }

    @Override
    public List<OrderItem> findOrderItemByUserIdAndOrderNum(Integer userId, String orderNum) {

        if(userId == null || orderNum == null){
            throw new SystemException("参数有误");
        }

        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.createCriteria().andUserIdEqualTo(userId)
                .andOrderNumEqualTo(orderNum);

        return orderItemMapper.selectByExample(orderItemExample);

    }
}
