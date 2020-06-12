package com.kj.commdityinfo.security.provider;

import com.kj.commdityinfo.security.bean.SmsAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author kj
 * @Date 2020/6/11 22:52
 * @Version 1.0
 */
public class SmsAuthenticationProvider implements AuthenticationProvider{

    private UserDetailsService userDetailsService;

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken smsAuthenticationToken = (SmsAuthenticationToken) authentication;
        //将用户名传入
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) smsAuthenticationToken.getPrincipal());

        if (userDetails == null){
            throw new InternalAuthenticationServiceException("未找到与该手机号对应的用户");
        }

        SmsAuthenticationToken returnToken = new SmsAuthenticationToken(userDetails, userDetails.getAuthorities());

        returnToken.setDetails(smsAuthenticationToken.getDetails());

        return returnToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (SmsAuthenticationToken.class
                .isAssignableFrom(authentication));
    }
}
