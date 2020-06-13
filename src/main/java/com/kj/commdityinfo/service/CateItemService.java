package com.kj.commdityinfo.service;

import com.kj.commdityinfo.bean.CateItem;

/**
 * @author kj
 * @Date 2020/6/12 12:52
 * @Version 1.0
 */
public interface CateItemService {

    /**
     * 通过id查询二级栏目
     * @param cateId
     * @return
     */
    CateItem findCateItemByCateId(Integer cateId);

}
