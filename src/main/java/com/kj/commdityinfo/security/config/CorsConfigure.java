package com.kj.commdityinfo.security.config;

import com.kj.commdityinfo.security.utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

/**
 * 配置spring security 的cors
 * @author kj
 * @Date 2020/6/14 10:07
 * @Version 1.0
 */
@Configuration
public class CorsConfigure {
    private static final List<String> ALLOWEDHEADERS = Arrays.asList(JwtUtils.TOKEN_HEADER);

    private static final List<String> METHODS = Arrays.asList("POST","GET","DELETE","PUT","OPTION");

    private static final List<String> EXPOSEDheaders = Arrays.asList(JwtUtils.TOKEN_HEADER);

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.setAllowedMethods(METHODS);
        corsConfiguration.setExposedHeaders(EXPOSEDheaders);
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(ALLOWEDHEADERS);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new org.springframework.web.filter.CorsFilter(urlBasedCorsConfigurationSource);
    }
}
