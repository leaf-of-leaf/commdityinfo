package com.kj.commdityinfo.security.config;

import com.kj.commdityinfo.security.utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * @author kj
 * @Date 2020/6/14 10:07
 * @Version 1.0
 */
@Configuration
public class CorsConfigure {
    private static final List<String> ALLOWEDHEADERS = Arrays.asList(JwtUtils.TOKEN_HEADER);

    private static final List<String> METHODS = Arrays.asList("POST","GET","DELETE","PUT","OPTION");

    private static final List<String> EXPOSEDheaders = Arrays.asList(JwtUtils.TOKEN_HEADER);

//    @Bean
//    public CorsConfigurationSource CorsConfigurationSource(){
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOrigin("/**");
//        //设置请求头
////        corsConfiguration.setAllowedHeaders(ALLOWEDHEADERS);
//        //设置请求方法
////        corsConfiguration.setAllowedMethods(METHODS);
//        //设置返回时允许加入的请求头
//        corsConfiguration.setExposedHeaders(EXPOSEDheaders);
//        //设置跨域时是否允许携带cookie等
//        corsConfiguration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
//        return urlBasedCorsConfigurationSource;
//    }
}
