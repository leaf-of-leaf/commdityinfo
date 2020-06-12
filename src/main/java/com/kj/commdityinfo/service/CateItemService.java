package com.kj.commdityinfo.service;

import com.kj.commdityinfo.bean.CateItem;

/**
 * @author kj
 * @Date 2020/6/12 12:52
 * @Version 1.0
 */
public interface CateItemService {

    CateItem findCateItemTitleByCateId(Integer cateId);

}
