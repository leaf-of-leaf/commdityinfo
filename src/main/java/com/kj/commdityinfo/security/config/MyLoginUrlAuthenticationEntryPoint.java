package com.kj.commdityinfo.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 结果返回JSON字符串
 * @author kj
 * @Date 2020/6/15 8:26
 * @Version 1.0
 */
@Component
public class MyLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    private static final String LOGINPATH = "/login";

    @Autowired
    private ObjectMapper objectMapper;

    public MyLoginUrlAuthenticationEntryPoint(){
        super(LOGINPATH);
        result = new HashMap<>();
        result.put("status", HttpStatus.UNAUTHORIZED.value());
        result.put("error", "error");
        result.put("message","未登录");
    }

    public final Map<String, Object> result;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        out.write(objectMapper.writeValueAsString(result));

        out.flush();
        out.close();
    }
}
