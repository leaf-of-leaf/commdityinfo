package com.kj.commdityinfo.security.config;

import com.kj.commdityinfo.security.utils.JwtUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author kj
 * @Date 2020/6/14 10:59
 * @Version 1.0
 */
@Configuration
@EnableWebMvc
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowCredentials(true)
                .maxAge(3600)
                .exposedHeaders(JwtUtils.TOKEN_HEADER);
    }
}
