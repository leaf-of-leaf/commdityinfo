package com.kj.commdityinfo.service;

import com.kj.commdityinfo.bean.Item;

import java.util.List;

/**
 * @author kj
 * @Date 2020/5/9 13:53
 * @Version 1.0
 */
public interface ItemService {


    /**
     * 查询所有商品
     * @return
     * @throws Exception
     */
    List<Item> findAllItem() throws Exception;


    /**
     * 通过分类id查询Item
     * @param cateId
     * @return
     * @throws Exception
     */
    List<Item> findItemsByCateId(Integer cateId) throws Exception;

    /**
     * 通过ItemId查询Item
     * @param id
     * @return
     */
    Item findItemByItemId(Integer id);

    /**
     * 保存或修改Item
     * @param item
     */
    void saveItem(Item item);

    /**
     * 通过ItemId删除item
     * @param itemId
     */
    void deleteItemByItemId(Integer itemId);



}
