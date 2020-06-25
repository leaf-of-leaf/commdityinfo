package com.kj.commdityinfo.utils;

import com.kj.commdityinfo.bean.Item;
import com.kj.commdityinfo.security.utils.JedisUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author kj
 * @Date 2020/6/25 11:03
 * @Version 1.0
 */
@Component
public class CacheItemPage {

//    @Autowired
//    private ItemService itemService;

//    public static int[] cacheItemId = new int[]{};
    public static List<Integer> cacheItemId = new ArrayList<>();

    public static List<Integer> getCacheItemId() {
        return cacheItemId;
    }

    public static void setCacheItemId(List<Integer> cacheItemId) {
        CacheItemPage.cacheItemId = cacheItemId;
    }

    static {
        //统计redis中对应的每个分类首页的itemId
        IntStream.range(1,13).forEach(cateId->{
        try{
            Object o = JedisUtils.get(JedisUtils.getCacheName(cateId));
            if(o instanceof List){
                List<Item> items = (List<Item>) o;
                for (Item item: items){
                    cacheItemId.add(Integer.valueOf(item.getItemId()));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        });
        System.out.println(cacheItemId);
    }
}
