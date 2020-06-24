package com.kj.commdityinfo.service.impl;

import com.kj.commdityinfo.bean.Item;
import com.kj.commdityinfo.bean.ItemExample;
import com.kj.commdityinfo.exception.SystemException;
import com.kj.commdityinfo.mapper.ItemMapper;
import com.kj.commdityinfo.security.utils.JedisUtils;
import com.kj.commdityinfo.service.ItemService;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kj
 * @Date 2020/5/9 13:53
 * @Version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired(required = false)
    private ItemMapper itemMapper;

    @Override
    public List<Item> findAllItem() throws Exception {
        return itemMapper.selectByExample(new ItemExample());
    }

    @Override
    public List<Item> findItemsByCateId(Integer cateId, Integer page, Integer pageSize) throws Exception {
        if(cateId == null || cateId <= 0){
            throw new SystemException("id不正确");
        }

        if(page <= 0 || pageSize <= 0){
            throw new SystemException("分页不正确");
        }

        ItemExample itemExample = new ItemExample();

        if(page == 1){
            Object o = JedisUtils.get(JedisUtils.getCacheName(cateId));
            if(o != null && o instanceof List && pageSize <= 20){
                List<Item> items = (List<Item>) o;
                return items;
            }
        }

        //分页查询
        itemExample.setLimit((page - 1) * pageSize);
        itemExample.setOffset(pageSize);

        itemExample.createCriteria().andCateIdEqualTo(cateId);
        return itemMapper.selectByExample(itemExample);
    }

    @Override
    public Item findItemByItemId(Integer id) {
        if(id == null || id <= 0){
            throw new SystemException("id不正确");
        }
        return itemMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveItem(Item item) {
        Integer itemId = item.getItemId();
        if(itemId <= 0){
            throw new SystemException("itemId不正确");
        }
        if(itemId == null){
            itemMapper.insertSelective(item);
        }
        if(itemMapper.isExistsByItemId(itemId) != null) {
            itemMapper.updateByPrimaryKeySelective(item);
        }
    }

    @Override
    public void deleteItemByItemId(Integer itemId) {
        itemMapper.deleteByPrimaryKey(itemId);
    }


    @Override
    public Long countByCateId(Integer cateId) {
        ItemExample itemExample = new ItemExample();
        itemExample.createCriteria().andCateIdEqualTo(cateId);
        return itemMapper.countByExample(itemExample);
    }

    @Override
    public List<Item> findItemByName(String itemName, Integer page, Integer pageSize) {

        if(StringUtils.isBlank(itemName)){
            throw new SystemException("名称为空");
        }

        ItemExample itemExample = new ItemExample();

        //分页查询
        itemExample.setLimit((page - 1) * pageSize);
        itemExample.setOffset(pageSize);

        itemExample.createCriteria().andNameLike("%" + itemName + "%");
        return itemMapper.selectByExample(itemExample);
    }

    @Override
    public Long countByName(String itemName) {
        ItemExample itemExample = new ItemExample();

        itemExample.createCriteria().andNameLike("%" + itemName + "%");
        return itemMapper.countByExample(itemExample);
    }
}
