package com.kj.commdityinfo.utils;

import com.kj.commdityinfo.bean.Item;
import com.kj.commdityinfo.bean.ItemExample;
import com.kj.commdityinfo.mapper.ItemMapper;
import com.kj.commdityinfo.security.utils.JedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * 把数据库中的商品数量同步到redis中
 * @author kj
 * @Date 2020/6/23 16:28
 * @Version 1.0
 */
@Component
public class AddItemNumToCache {

    @Autowired(required = false)
    private ItemMapper itemMapper;

    public List<Item> getItems(){
        ItemExample itemExample = new ItemExample();
        return itemMapper.selectByExample(itemExample);
    }

    public void save(List<Item> items){

        HashMap<String, String> map = new HashMap<>(16);
        for(Item item : items){
            String key = "item" + item.getItemId();
            String num = item.getNum().toString();
            String price = item.getPrice().toString();
            map.put("num",num);
            map.put("price",price);
            JedisUtils.hmset(key, map);
            map.clear();
        }
    }
}
