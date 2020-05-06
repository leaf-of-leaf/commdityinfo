package com.kj.commdityinfo.aop;

import com.kj.commdityinfo.bean.Log;
import com.kj.commdityinfo.security.utils.JwtUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
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

    @Pointcut("execution(public * com.kj.commdityinfo.controller.DemoController.*(..)))")
    public void pointCutMethod(){ }

    @Around("pointCutMethod()")
    public void doAround(ProceedingJoinPoint pjp){

        Log log = new Log();
        HttpServletRequest request = null;
        //获取参数
        Object[] args = pjp.getArgs();
        //哪个用户进行了操作
        if(args.length > 0){
            for(Object o : args) {
                if(o instanceof NativeWebRequest){
                    NativeWebRequest nativeWebRequest = (NativeWebRequest) o;
                    request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
                    String authorization = request.getHeader(JwtUtils.TOKEN_HEADER);
                    String token = authorization.replaceAll(JwtUtils.TOKEN_PREFIX, "");
                    log.setToken(token);
                }
            }
        }
        try{
                //前置
            Long startTime = System.currentTimeMillis();
            pjp.proceed(args);
            Long endTime = System.currentTimeMillis();
                //后置
            log.setUrl(request.getRequestURI());
            log.setIp(request.getRemoteAddr());
            log.setPort(request.getRemotePort());
            log.setSpendTime(endTime - startTime);
        } catch(Throwable t){
                //异常通知
            log.setException(t.getMessage());
        } finally{
                //最终通知
        }

        System.out.println(log);
    }
}
