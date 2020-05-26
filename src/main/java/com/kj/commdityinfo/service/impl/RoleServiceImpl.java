package com.kj.commdityinfo.service.impl;

import com.kj.commdityinfo.bean.Role;
import com.kj.commdityinfo.exception.SystemException;
import com.kj.commdityinfo.mapper.RoleMapper;
import com.kj.commdityinfo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kj
 * @Date 2020/5/10 17:01
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role findRoleByRoleId(Integer id) throws Exception{
        if(id == null || id <= 0){
            throw new SystemException("id不正确");
        }
        return roleMapper.selectByPrimaryKey(id);
    }
}
