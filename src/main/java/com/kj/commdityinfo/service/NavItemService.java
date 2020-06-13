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
     * @return
     */
    List<NavItem> findAllNavItem();

    /**
     * 通过id查询一级栏目
     * @param navItemId
     * @return
     */
    NavItem findNavItemByNavItemId(Integer navItemId);

    /**
     * 通过Name查询一级栏目
     * @param navItemName
     * @return
     */
    NavItem findNavItemByNavItemName(String navItemName);

}
