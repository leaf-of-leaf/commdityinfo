package com.kj.commdityinfo.aop;

import com.kj.commdityinfo.bean.Log;
import com.kj.commdityinfo.security.utils.JwtUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 日志切面信息
 * @author kj
 * @Date 2020/5/6 15:15
 * @Version 1.0
 */
@Aspect
@Component
/**
 * order越小order越高
 */
@Order(1)
public class LogMonitor {

    @Autowired(required = false)
    private NativeWebRequest nativeWebRequest;

    @Pointcut("execution(public * com.kj.commdityinfo.controller.*.*(..)))")
    public void pointCutMethod(){ }

    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp){
        Object result = null;
        Log log = new Log();
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        //获取参数
        Object[] args = pjp.getArgs();
        //设置token
        String authorization = request.getHeader(JwtUtils.TOKEN_HEADER);
//        String token = authorization.replaceAll(JwtUtils.TOKEN_PREFIX, "");
//        log.setToken(token);
        try{
                //前置
            Long startTime = System.currentTimeMillis();
            result = pjp.proceed(args);
            Long endTime = System.currentTimeMillis();
                //后置
            log.setUrl(request.getRequestURI());
            log.setIp(request.getRemoteAddr());
            log.setPort(request.getRemotePort());
            log.setSpendTime(endTime - startTime);
            log.setParams(Arrays.toString(args));
        } catch(Throwable t){
                //异常通知
            log.setException(t.getMessage());
        } finally{
                //最终通知
        }
        System.out.println(log);
        //要返回结果，不然springmvc返回null
        return result;
    }
}
