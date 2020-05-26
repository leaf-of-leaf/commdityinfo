package com.kj.commdityinfo.service;

import com.kj.commdityinfo.bean.NavItem;

import java.util.List;

/**
 * @author kj
 * @Date 2020/5/10 12:30
 * @Version 1.0
 */
public interface NavItemService {
    /**
     * 查询所有的一级栏目
     * 其中会包含二级栏目
     * @return
     */
    List<NavItem> findAllNavItem();

}
