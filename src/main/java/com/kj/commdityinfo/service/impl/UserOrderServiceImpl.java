package com.kj.commdityinfo.service.impl;

import com.kj.commdityinfo.bean.Item;
import com.kj.commdityinfo.bean.OrderItem;
import com.kj.commdityinfo.bean.UserOrder;
import com.kj.commdityinfo.bean.UserOrderExample;
import com.kj.commdityinfo.exception.SystemException;
import com.kj.commdityinfo.mapper.ItemMapper;
import com.kj.commdityinfo.mapper.UserOrderMapper;
import com.kj.commdityinfo.security.utils.JedisUtils;
import com.kj.commdityinfo.service.OrderItemService;
import com.kj.commdityinfo.service.UserOrderService;
import com.kj.commdityinfo.utils.CacheItemPage;
import com.kj.commdityinfo.utils.DoubleUtil;
import com.kj.commdityinfo.utils.MyThreadPool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.sql.SQLException;
import java.util.*;

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

    @Autowired(required = false)
    private ItemMapper itemMapper;

    @Autowired
    private MyThreadPool myThreadPool;




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
//    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void deleteUserOrderByOrderNum(String orderNum) {

        if(orderNum == null){
            throw new SystemException("参数有误");
        }

        List<OrderItem> orderItemByOrderNum = orderItemService.findOrderItemByOrderNum(orderNum);
        //把redis中的库存加回来,同时mysql库存也要加回来
        for (OrderItem orderItem : orderItemByOrderNum){
            //原子性操作，不用加锁
            JedisUtils.hincrby("item" + orderItem.getItemId(), "num",Long.valueOf(orderItem.getBuyCounts().toString()));
            //因为通过主键索引，且为innoDB,所以默认为行锁 且为num = num + #{num}
            //加入任务队列中执行就好
            myThreadPool.add(()->itemMapper.updateItemByitemIdAndNum(orderItem.getItemId(),orderItem.getBuyCounts()));
            //如果涉及到首页中数据，则将对应首页的缓存置空
            if(CacheItemPage.getCacheItemId().contains(orderItem.getItemId())){
                JedisUtils.del(JedisUtils.getCacheName(orderItem.getItem().getCateId()), 1);
            }
        }

        //删除订单项
        orderItemService.deleteOrderItemByOrderNum(orderNum);

        //删除用户订单订单
        UserOrderExample userOrderExample = new UserOrderExample();
        userOrderExample.createCriteria().andOrderNumEqualTo(orderNum);
        userOrderMapper.deleteByExample(userOrderExample);

    }

    @Override
    //当多个商品扣除数量时,当其中一个过长的阻塞，直接回滚
//    @Transactional(rollbackFor = Exception.class,isolation = Isolation.READ_COMMITTED, timeout = 5)
    public void insertUserOrder(UserOrder userOrder) throws Exception {
        if(userOrder == null){
            throw new SystemException("参数有误");
        }
        if(StringUtils.isBlank(userOrder.getOrderDescription()) || StringUtils.isBlank(userOrder.getOrderName())){
            throw new SystemException("参数有误");
        }


        List<OrderItem> orderItemByUserId = orderItemService.findOrderItemByUserIdAndOrderNum(userOrder.getUserId(), "");
        Double total = 0d;

        List<Runnable> runnables = new LinkedList<>();

        //将购物车中的每个商品的库存减去
        Transaction multi = null;
        try{
            Jedis resource = JedisUtils.getResource();
            //redis开启事务
            multi = resource.multi();
            for(OrderItem orderItem: orderItemByUserId){
                //扣除num
                Map<String, String> hmget = JedisUtils.hmget("item" + orderItem.getItemId(), "num", "price");
                Integer numInRedis = Integer.valueOf(hmget.get("num"));

                int i = 0;
                do{
                    //分布式锁的实现
                    if(!JedisUtils.setnx("lock_item"+ orderItem.getItemId() +"_sub", "1").equals(Long.valueOf(0))){
                        subNum(orderItem.getItemId(),numInRedis,orderItem.getBuyCounts(), multi, runnables);
                        break;
                    }
                    Thread.sleep(((int)(Math.random() * 2) + 1));
                }while ((i++) < 2);
                if(i == 2){
                    throw new SystemException("锁竞争激烈，两次均为拿到锁");
                }
                //执行商品的扣除时，如果该商品在首页中，将首页缓存删除
                if(CacheItemPage.getCacheItemId().contains(orderItem.getItemId())){
                    //key为二进制,状态为1
                    JedisUtils.del(JedisUtils.getCacheName(orderItem.getItem().getCateId()), 1);
                }
            }
            //没出错误时执行插入操作，将要执行的任务放入线程池中自己执行
            for (Runnable runnable: runnables){
                myThreadPool.add(runnable);
            }
            //redis结束事务
            multi.exec();
            System.out.println("事务执行完成");
        }catch (Exception e){
            runnables.clear();
            multi.discard();
            throw new SystemException(e.getMessage());
        }

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

    /**
     * 业务逻辑操作
     */
    private void subNum(Integer itemId, Integer numInRedis, Integer numInRequest, Transaction transaction, List<Runnable> runnables) throws Exception{

        JedisUtils.expire("lock_item" + itemId + "_sub", 5);

        //业务流程
        Integer result =  numInRedis - numInRequest;
        if(result >= 0){
            HashMap<String, String> map = new HashMap<>(16);
            map.put("num", result + "");
            transaction.hmset("item" + itemId, map);
            try{
//                myThreadPool.add(()->{
//                    itemMapper.updateItemByitemIdAndNum(itemId, (-numInRequest));
//                });
                runnables.add(()->{
                    itemMapper.updateItemByitemIdAndNum(itemId, (-numInRequest));
                });
            } catch (Exception e){
                throw new SystemException(e.getMessage());
            }
        }else {
            //操作完成以后手动释放锁
            JedisUtils.del("lock_item"+ itemId +"_sub", 0);
            throw new SystemException("检测到购买数量与库存不符,请对商品数量进行核查");
        }

        //操作完成以后手动释放锁
        JedisUtils.del("lock_item"+ itemId +"_sub", 0);
    }
}


