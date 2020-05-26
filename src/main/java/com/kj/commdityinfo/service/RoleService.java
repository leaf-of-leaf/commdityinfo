package com.kj.commdityinfo.service;

import com.kj.commdityinfo.bean.Role;

/**
 * @author kj
 * @Date 2020/5/10 17:00
 * @Version 1.0
 */
public interface RoleService {

    /**
     * 通过roleId找到对应的role
     * @param id
     * @return
     */

    Role findRoleByRoleId(Integer id) throws Exception;

}
