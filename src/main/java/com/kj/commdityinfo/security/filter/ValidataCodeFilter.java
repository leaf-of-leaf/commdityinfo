package com.kj.commdityinfo.security.filter;

import com.kj.commdityinfo.security.controller.ValidataController;
import com.kj.commdityinfo.security.exception.ValidateCodeException;
import com.kj.commdityinfo.security.utils.HttpHandlerUtils;
import com.kj.commdityinfo.security.utils.JedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kj
 * @Date 2020/4/23 19:37
 * @Version 1.0
 * 图形验证码校验过滤器
 */
@Component
public class ValidataCodeFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        String loginUrl = "/login";

        String loginPhoneUrl = "/login/mobile";

        String loginMethod = "POST";

        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        if (loginUrl.equals(requestURI)
                && loginMethod.equals(method)) {
            try {
                ValidataCode(request);
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        if (loginPhoneUrl.equals(requestURI)
                && loginMethod.equals(method)) {
            try {
                checkPhoneCode(request);
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void ValidataCode(HttpServletRequest request) throws ValidateCodeException {

        //用户发送来的图形验证码
        String codeInRequest = request.getParameter("ImageCode");
        //产生的uuid用来辨识不同用户之间的图形验证码
        String uuid = HttpHandlerUtils.getCookeiValue(request,"uuid");
        //redis中存放的正确的图形验证码
        String codeInRedis = (String) JedisUtils.get(uuid);
        System.out.println("uuid:" + uuid);
        System.out.println("codeInRequest:" + codeInRequest);
        System.out.println("codeInRedis:" + codeInRedis);
        //此时只有图形验证码存在
        if (StringUtils.isEmpty(codeInRequest)) {
            throw new ValidateCodeException("图形验证码不能为空！");
        }
        if (codeInRedis == null) {
            throw new ValidateCodeException("图形验证码已过期");
        }
        if (!codeInRedis.equals(codeInRequest)) {
            throw new ValidateCodeException("图形验证码不正确！");
        }
        //验证码正确但登录密码错误时，验证码要刷新才行
    }

    private void checkPhoneCode(HttpServletRequest request) throws ValidateCodeException {

        //手机号码
        String num = request.getParameter("phone");
        //用户发送来的手机验证码
        String phoneCode = request.getParameter("phoneCode");


        //手机号码存在
        if(!StringUtils.isBlank(num)){
            //redis中存放的正确的手机验证码
            String phoneCodeInRedis = (String) JedisUtils.get(num);
            if(phoneCodeInRedis == null){
                throw new ValidateCodeException("手机验证码已过期！");
            }
            if(phoneCodeInRedis.equals(phoneCode)){
                return;
            }else {
                throw new ValidateCodeException("手机验证码错误！");
            }
        }
    }
}
