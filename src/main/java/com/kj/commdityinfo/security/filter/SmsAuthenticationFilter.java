package com.kj.commdityinfo.security.filter;

import com.kj.commdityinfo.security.bean.MyUser;
import com.kj.commdityinfo.security.bean.SmsAuthenticationToken;
import com.kj.commdityinfo.security.handler.FailureAuthenticationHandler;
import com.kj.commdityinfo.security.handler.SuccessAuthenticationHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kj
 * @Date 2020/6/11 22:18
 * @Version 1.0
 */
public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter  {

    private AuthenticationManager authenticationManager;

    public SmsAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher("/login/mobile", "POST"));
        this.authenticationManager = authenticationManager;
        this.setSessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy());
        setAuthenticationSuccessHandler(new SuccessAuthenticationHandler());
        setAuthenticationFailureHandler(new FailureAuthenticationHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        MyUser user = parseUser(request);
        SmsAuthenticationToken smsAuthenticationToken = new SmsAuthenticationToken(user.getUserName());
        return this.authenticationManager.authenticate(smsAuthenticationToken);
    }

    public static MyUser parseUser(HttpServletRequest request) {
        MyUser user = new MyUser();
        String username = request.getParameter("phone");
        String password = null;
        if(username == null){
            username = "";
        }
        if(password == null){
            password = "";
        }
        user.setUserName(username);
        user.setPassword(password);
        return user;
    }
}
