package com.kj.commdityinfo.controller;

import com.kj.commdityinfo.bean.CateItem;
import com.kj.commdityinfo.service.CateItemService;
import com.kj.commdityinfo.utils.Message;
import com.kj.commdityinfo.utils.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kj
 * @Date 2020/6/12 13:03
 * @Version 1.0
 */
@Api(tags = "二级栏目查询")
@RestController
public class CateItemController {

    @Autowired
    private CateItemService cateItemService;
    @ApiOperation(value = "通过id查询二级栏目")
    @GetMapping("/findCateItemByCateId")
    public Message<CateItem> findCateItemByCateId(Integer cateId){
        return MessageUtil.success(cateItemService.findCateItemByCateId(cateId));
    }

}
