package com.kj.commdityinfo.security.config;

import com.kj.commdityinfo.mapper.UserMapper;
import com.kj.commdityinfo.security.bean.MyUser;
import com.kj.commdityinfo.security.exception.ValidateCodeException;
import com.kj.commdityinfo.service.UserService;
import org.apache.commons.lang3.StringUtils;
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
    private UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        //此处进行查询数据库操作，例如手机号有没有注册，但便于测试，便直接返回User了
        if(StringUtils.isBlank(mobile)){
            throw new ValidateCodeException("验证失败,手机号为空");
        }
        System.out.println("mobile:" + mobile);
        com.kj.commdityinfo.bean.User userAndRoleByMobile = userService.findUserAndRoleByMobile(mobile);

        if(userAndRoleByMobile == null){
            throw new ValidateCodeException("验证失败,该手机还未注册");
        }

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
