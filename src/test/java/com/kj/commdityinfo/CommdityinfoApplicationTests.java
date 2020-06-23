package com.kj.commdityinfo;

import com.kj.commdityinfo.bean.Item;
import com.kj.commdityinfo.mapper.ItemMapper;
import com.kj.commdityinfo.service.ItemService;
import com.kj.commdityinfo.utils.ExcelReadUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CommdityinfoApplicationTests {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemMapper itemMapper;

    @Test
    void contextLoads() {
        try{
            List<Item> items = ExcelReadUtils.getItems();
            for (Item item : items){
                itemMapper.insert(item);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
