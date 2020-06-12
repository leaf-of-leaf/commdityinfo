package com.kj.commdityinfo.security.config;

import com.kj.commdityinfo.security.bean.MyUser;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author kj
 * @Date 2020/6/11 23:09
 * @Version 1.0
 */
public class SmsUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //此处进行查询数据库操作，例如手机号有没有注册，但便于测试，便直接返回User了
        User u = new User(username, username,
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return u;
    }
}
