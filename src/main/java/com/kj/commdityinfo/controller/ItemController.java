package com.kj.commdityinfo.controller;

import com.kj.commdityinfo.bean.Item;
import com.kj.commdityinfo.service.ItemService;
import com.kj.commdityinfo.utils.Message;
import com.kj.commdityinfo.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kj
 * @Date 2020/6/12 13:02
 * @Version 1.0
 */
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;


    @GetMapping("/findAllItem")
    public Message<Object> findAllItem(){
        try{
            return MessageUtil.success(itemService.findAllItem());
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }

    @GetMapping("/findItemsByCateId")
    public Message<Object> findItemsByCateId(Integer cateId){
        try{
            return MessageUtil.success(itemService.findItemsByCateId(cateId));
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }

    @GetMapping("/findItemByItemId")
    public Message<Object> findItemByItemId(Integer itemId){
        try{
            return MessageUtil.success(itemService.findItemByItemId(itemId));
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }

    @PostMapping("/saveItem")
    public Message<Object> saveItem(Item item){
        try{
            itemService.saveItem(item);
            return MessageUtil.success("成功");
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }

    @GetMapping("/deleteItemByItemId")
    public Message<Object> deleteItemByItemId(Integer itemId){
        try{
            itemService.deleteItemByItemId(itemId);
            return MessageUtil.success("成功");
        } catch (Exception e){
            return MessageUtil.error(400, e.getMessage());
        }
    }
}