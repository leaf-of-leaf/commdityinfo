package com.kj.commdityinfo.service.impl;

import com.kj.commdityinfo.bean.OrderItem;
import com.kj.commdityinfo.bean.UserOrder;
import com.kj.commdityinfo.bean.UserOrderExample;
import com.kj.commdityinfo.exception.SystemException;
import com.kj.commdityinfo.mapper.UserOrderMapper;
import com.kj.commdityinfo.service.OrderItemService;
import com.kj.commdityinfo.service.UserOrderService;
import com.kj.commdityinfo.utils.DoubleUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author kj
 * @Date 2020/6/12 20:32
 * @Version 1.0
 */
@Service
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired(required = false)
    private OrderItemService orderItemService;

    @Autowired(required = false)
    private UserOrderMapper userOrderMapper;



    @Override
    public UserOrder findUserOrderByOrderId(Integer orderId) {
        if(orderId == null){
            throw new SystemException("参数有误");
        }

        return userOrderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public UserOrder findUserOrderByOrderNum(String orderNum) {
        if(StringUtils.isBlank(orderNum)){
            throw new SystemException("参数有误");
        }

        UserOrderExample userOrderExample = new UserOrderExample();
        userOrderExample.createCriteria().andOrderNumEqualTo(orderNum);

        //因为订单号为unique，因此只会有一个
        return userOrderMapper.selectByExample(userOrderExample).get(0);
    }

    @Override
    public List<UserOrder> findUserOrderByUserId(Integer userId) {
        if(userId == null){
            throw new SystemException("参数有误");
        }

        UserOrderExample userOrderExample = new UserOrderExample();
        userOrderExample.createCriteria().andUserIdEqualTo(userId);

        List<UserOrder> userOrders = userOrderMapper.selectByExample(userOrderExample);
        System.out.println(userOrders);

        //因为订单号为unique，因此只会有一个
        //return userOrderMapper.selectByExample(userOrderExample);
        return userOrders;
    }


    @Override
    public void deleteUserOrderByUserId(Integer userId) {
        if(userId == null){
            throw new SystemException("参数有误");
        }

        UserOrderExample userOrderExample = new UserOrderExample();
        userOrderExample.createCriteria().andUserIdEqualTo(userId);
        userOrderMapper.deleteByExample(userOrderExample);
    }

    @Override
    public void deleteUserOrderByOrderNum(String orderNum) {
        if(orderNum == null){
            throw new SystemException("参数有误");
        }

        UserOrderExample userOrderExample = new UserOrderExample();
        userOrderExample.createCriteria().andOrderNumEqualTo(orderNum);
        userOrderMapper.deleteByExample(userOrderExample);
    }

    @Override
    public void insertUserOrder(UserOrder userOrder) throws Exception {
        if(userOrder == null){
            throw new SystemException("参数有误");
        }
        if(StringUtils.isBlank(userOrder.getOrderDescription()) || StringUtils.isBlank(userOrder.getOrderName())){
            throw new SystemException("参数有误");
        }


        List<OrderItem> orderItemByUserId = orderItemService.findOrderItemByUserIdAndOrderNum(userOrder.getUserId(), "");
        Double total = 0d;

        //订单号生成
        List<Integer> orderItemIds = new ArrayList<>();
        for (OrderItem orderItem: orderItemByUserId){
            orderItemIds.add(orderItem.getOrderItemId());
            total = DoubleUtil.plus(total,orderItem.getPaid());
        }

        if(total.equals(0d)){
            throw new SystemException("订单项异常,总价格为0");
        }
        //设置订单号
        String orderNum = new Long(System.currentTimeMillis()).toString() + userOrder.getUserId().toString() + "";
        userOrder.setOrderNum(orderNum);
        //设置订单总价
        userOrder.setPaidAccount(total);
        //设置订单创建时间
        userOrder.setCreateTime(new Date(System.currentTimeMillis()));
        //设置支付状态 0为未支付,1为已支付
        userOrder.setStatus(0);

        Integer[] ids  = new Integer[]{};

        //创建订单时，修改该用户对应购物车中每个订单项，便于通过订单号查询该订单下的所有订单项
        orderItemService.updateOrderItems(orderItemIds.toArray(ids), orderNum);

        userOrderMapper.insert(userOrder);

    }

    @Override
    public void updateUserOrderStatusByOrderNum(Integer status, String orderNum) {
        UserOrder userOrder = new UserOrder();
        userOrder.setStatus(status);

        UserOrderExample userOrderExample = new UserOrderExample();
        userOrderExample.createCriteria().andOrderNumEqualTo(orderNum);

        userOrderMapper.updateByExampleSelective(userOrder, userOrderExample);
    }
}
