package com.kj.commdityinfo.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kj.commdityinfo.security.bean.FilterPath;
import com.kj.commdityinfo.security.filter.JwtAuthenticationFilter;
import com.kj.commdityinfo.security.filter.JwtAuthorityFilter;
import com.kj.commdityinfo.security.filter.SmsAuthenticationFilter;
import com.kj.commdityinfo.security.filter.ValidataCodeFilter;
import com.kj.commdityinfo.security.provider.SmsAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import java.util.Arrays;

/**
 * @author kj
 * @Date 2020/4/28 16:15
 * @Version 1.0
 * security 主配置类
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private FilterPath filterPath;

    @Autowired
    private ValidataCodeFilter validataCodeFilter;

    @Autowired
    private SmsUserDetailsService smsUserDetailsService;

    @Autowired(required = false)
    private DaoAuthenticationProvider daoAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //需要添加添加配置，不然authenticationManager()返回为null
        //同时可以配置provder和UserDeatisService
        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
        smsAuthenticationProvider.setUserDetailsService(smsUserDetailsService);
        auth.authenticationProvider(smsAuthenticationProvider);
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //允许加载的静态资源路径
        web.ignoring().antMatchers("/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .and()
                .authorizeRequests() // 授权配置
                //登录跳转 URL 无需认证
                .antMatchers(filterPath.getStrings()).permitAll()
                .anyRequest()    // 所有请求
                .authenticated() // 都需要认证
                .and()
                .csrf().disable()
                .addFilterBefore(validataCodeFilter, UsernamePasswordAuthenticationFilter.class)
                //token的授权
                .addFilter(new JwtAuthorityFilter(authenticationManager()))
                //用户名密码登录
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                //手机验证码登录
                .addFilterBefore(new SmsAuthenticationFilter(authenticationManager()), JwtAuthenticationFilter.class)
                //禁用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//                .apply(jwtAuthenticationConfig)
//                .and()
                .cors()  //支持跨域
                .and()   //添加header设置，支持跨域和ajax请求
                .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
                new Header("Access-Control-Expose-Headers","Authorization"),
                new Header("Access-Control-Allow-Credentials", "true"))));
    }

    @Bean
    public BCryptPasswordEncoder BCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    protected AuthenticationProvider daoAuthenticationProvider() throws Exception{
        //这里会默认使用BCryptPasswordEncoder比对加密后的密码，注意要跟createUser时保持一致
        DaoAuthenticationProvider daoProvider = null;
        if(daoAuthenticationProvider != null) {
            daoProvider = daoAuthenticationProvider;
        }else{
            daoProvider = new DaoAuthenticationProvider();
        }
        daoProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        daoProvider.setUserDetailsService(myUserDetailsService);
        return daoProvider;
    }

    @Bean
    public ObjectMapper ObjectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public FilterPath FilterPath(){
        return new FilterPath(new String[]{
                "/authentication/require",
                "/login.html",
                "/imgcode",
                "/smscode",
                "/signout/success",
                "/code/img",
                "/code/sms",
                //swagger中的路径
                "/swagger*//**",
                "/v2/api-docs",
                "/webjars*//**",
                "/phonepay",
                "/return",
                "/notify"
        });
    }

}
