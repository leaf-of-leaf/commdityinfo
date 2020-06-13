package com.kj.commdityinfo.service.impl;

import com.kj.commdityinfo.bean.NavItem;
import com.kj.commdityinfo.bean.NavItemExample;
import com.kj.commdityinfo.mapper.NavItemMapper;
import com.kj.commdityinfo.service.NavItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kj
 * @Date 2020/5/10 12:32
 * @Version 1.0
 */
@Service
public class NavItemServiceImpl implements NavItemService {

    @Autowired(required = false)
    private NavItemMapper navItemMapper;

    @Override
    public List<NavItem> findAllNavItem() {
        NavItemExample navItemExample = new NavItemExample();
        return navItemMapper.selectByExample(navItemExample);
    }

    @Override
    public NavItem findNavItemByNavItemId(Integer navItemId) {
        return navItemMapper.selectByPrimaryKey(navItemId);
    }

    @Override
    public NavItem findNavItemByNavItemName(String navItemName) {
        NavItemExample navItemExample = new NavItemExample();
        navItemExample.createCriteria().andTitleEqualTo(navItemName);
        return navItemMapper.selectByExample(navItemExample).get(0);
    }
}
