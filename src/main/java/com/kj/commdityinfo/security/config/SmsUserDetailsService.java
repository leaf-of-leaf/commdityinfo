package com.kj.commdityinfo.security.config;

import com.kj.commdityinfo.mapper.UserMapper;
import com.kj.commdityinfo.security.bean.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author kj
 * @Date 2020/6/11 23:09
 * @Version 1.0
 */
@Component
public class SmsUserDetailsService implements UserDetailsService {

    @Autowired(required = false)
    private UserMapper userMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        //此处进行查询数据库操作，例如手机号有没有注册，但便于测试，便直接返回User了

        com.kj.commdityinfo.bean.User userAndRoleByMobile = userMapper.findUserAndRoleByMobile(mobile);

        MyUser user = new MyUser();
        user.setUserName(userAndRoleByMobile.getName());
        user.setPassword(bCryptPasswordEncoder.encode(userAndRoleByMobile.getPassword()));
        user.setAuthority(userAndRoleByMobile.getRole().getName());

        User u = new User(user.getUserName(), user.getPassword(),
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthority()));
        return u;
    }
}
