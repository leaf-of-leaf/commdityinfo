package com.kj.commdityinfo.security.filter;

import com.kj.commdityinfo.security.bean.MyUser;
import com.kj.commdityinfo.security.handler.FailureAuthenticationHandler;
import com.kj.commdityinfo.security.handler.SuccessAuthenticationHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kj
 * @Date 2020/4/28 16:22
 * @Version 1.0
 * 自定义Jwt认证过滤器
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.setSessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy());
        setAuthenticationSuccessHandler(new SuccessAuthenticationHandler());
        setAuthenticationFailureHandler(new FailureAuthenticationHandler());
    }

    public JwtAuthenticationFilter(){}



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        MyUser user = parseUser(request);
        System.out.println(request.getRequestURI());
        UsernamePasswordAuthenticationToken uToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        return this.authenticationManager.authenticate(uToken);
    }

    public static MyUser parseUser(HttpServletRequest request) {
        MyUser user = new MyUser();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username:" + username);
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
