package com.kj.commdityinfo.security.config;

import com.kj.commdityinfo.security.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @author kj
 * @Date 2020/6/14 10:59
 * @Version 1.0
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Value("${cors.origin}")
    private String origin;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(origin)
                .allowCredentials(true)
                .maxAge(3600)
                .exposedHeaders(JwtUtils.TOKEN_HEADER);
    }

    /**
     *
     * @param registry
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
}
