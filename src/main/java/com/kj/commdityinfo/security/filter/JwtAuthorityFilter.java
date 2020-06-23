package com.kj.commdityinfo.security.filter;

import com.kj.commdityinfo.security.exception.JwtAuthenticationException;
import com.kj.commdityinfo.security.utils.HttpHandlerUtils;
import com.kj.commdityinfo.security.utils.JedisUtils;
import com.kj.commdityinfo.security.utils.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.stream.IntStream;

/**
 * @author kj
 * @Date 2020/4/28 19:03
 * @Version 1.0
 * 自定义Jwt授权过滤器
 */
public class JwtAuthorityFilter extends BasicAuthenticationFilter {

    public JwtAuthorityFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {

        HttpHandlerUtils.successMessage(response);

    }


    @Override
    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.setStatus(HttpURLConnection.HTTP_UNAUTHORIZED);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String authorization = request.getHeader(JwtUtils.TOKEN_HEADER);


        if(StringUtils.isEmpty(authorization)){
            chain.doFilter(request,response);
            return;
        }

        if(!authorization.startsWith(JwtUtils.TOKEN_PREFIX)){
            onUnsuccessfulAuthentication(request,response,new JwtAuthenticationException("客户端传来的令牌出现错误"));
            chain.doFilter(request,response);
            return;
        }

        String token = authorization.replaceAll(JwtUtils.TOKEN_PREFIX, "");

        String username = null;
        try{
            username = JwtUtils.getUsername(token);
        }
        //超过令牌有效期
        catch (ExpiredJwtException e) {
            System.out.println("已过期");
            chain.doFilter(request,response);
            return;
        }
        //令牌无效
        catch (Exception e){
            System.out.println("令牌无效");
            chain.doFilter(request,response);
            return;
        }

        //从传来的token中解析出来的用户名去获取redis中token
        String oldToken = (String) JedisUtils.get(username);

        //用来解决token刷新以后，旧的token还能登陆的问题
        if(StringUtils.isEmpty(oldToken) || !oldToken.equals(token)) {
            onUnsuccessfulAuthentication(request,response,new JwtAuthenticationException("token不正确"));
            chain.doFilter(request,response);
            return;
        }

        //已过可用期，但处于可刷新期,即处于可用期与有效期之间
        if(JwtUtils.isRefreshToken(token)){
            System.out.println("token进行刷新");
            //创造新的token
            String refreshToken = JwtUtils.createToken(username, JwtUtils.getUserRole(token));
            JedisUtils.setex(username, JedisUtils.TIME ,refreshToken);
            response.setHeader(JwtUtils.TOKEN_HEADER,JwtUtils.TOKEN_PREFIX + refreshToken);
            Authentication authentication = HttpHandlerUtils.getAuthentication(username, refreshToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request,response);
            return;
        }

        //token可用时，授权
        if(SecurityContextHolder.getContext().getAuthentication() == null){
            Authentication authentication = HttpHandlerUtils.getAuthentication(username, token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            onSuccessfulAuthentication(request,response,authentication);
        }
        chain.doFilter(request,response);

    }
}
