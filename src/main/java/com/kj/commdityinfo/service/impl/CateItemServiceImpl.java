package com.kj.commdityinfo.service.impl;

import com.kj.commdityinfo.bean.CateItem;
import com.kj.commdityinfo.mapper.CateItemMapper;
import com.kj.commdityinfo.service.CateItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kj
 * @Date 2020/6/12 12:53
 * @Version 1.0
 */
@Service
public class CateItemServiceImpl implements CateItemService {

    @Autowired(required = false)
    private CateItemMapper cateItemMapper;

    @Override
    public CateItem findCateItemByCateId(Integer cateId) {
        return cateItemMapper.selectByPrimaryKey(cateId);
    }
}
