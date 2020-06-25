package com.kj.commdityinfo;

import com.kj.commdityinfo.bean.Item;
import com.kj.commdityinfo.mapper.ItemMapper;
import com.kj.commdityinfo.security.utils.JedisUtils;
import com.kj.commdityinfo.service.ItemService;
import com.kj.commdityinfo.utils.AddItemNumToCache;
import com.kj.commdityinfo.utils.ExcelReadUtils;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
class CommdityinfoApplicationTests {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private AddItemNumToCache addItemNumToCache;

    /**
     * execl表格读取
     */
    @Test
    void contextLoads() {
//        try{
//            List<Item> items = ExcelReadUtils.getItems();
//            for (Item item : items){
//                itemMapper.insert(item);
//            }
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//        }
    }

    /**
     * 库存的缓存
     */
    @Test
    public void run(){
        //保存时，key为String
//        try{
//            addItemNumToCache.save(addItemNumToCache.getItems());
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
    }

    /**
     * 首页的缓存
     */
    @Test
    public void run2(){
        //保存时，key为二进制
//        IntStream.range(1,13).forEach(cateId->{
//            try{
//                List<Item> itemsByCateId = itemService.findItemsByCateId(cateId, 1, 20);
//                JedisUtils.set(JedisUtils.getCacheName(cateId), itemsByCateId);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        });
    }

    @Test
    public void run3(){
//        Object o = JedisUtils.get(JedisUtils.getCacheName(1));
//        if(o instanceof List){
//            List<Item> items = (List<Item>) o;
//            System.out.println(items);
//        }
    }
}
