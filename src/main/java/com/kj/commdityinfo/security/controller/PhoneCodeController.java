package com.kj.commdityinfo.security.controller;

import com.kj.commdityinfo.security.utils.JedisUtils;
import com.kj.commdityinfo.security.utils.ZhenziSmsUtils;
import com.kj.commdityinfo.utils.Message;
import com.kj.commdityinfo.utils.MessageUtil;
import com.zhenzi.sms.ZhenziSmsClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @author kj
 * @Date 2020/6/11 18:53
 * @Version 1.0
 */
@RestController
public class PhoneCodeController {
    //在redis中存放的时间,即手机验证码过期时间

    private static Integer EXPIRETIME;


    private static Boolean ENABLESMS;

    @Value("${code.sms}")
    public void setEXPIRETIME(Integer EXPIRETIME) {
        PhoneCodeController.EXPIRETIME = EXPIRETIME;
    }

    @Value("${code.enableSms}")
    public void setENABLESMS(Boolean ENABLESMS) {
        PhoneCodeController.ENABLESMS = ENABLESMS;
    }

    @GetMapping("/code/sms")
    public Message getPhoneCode(HttpServletRequest request){
        String num = request.getParameter("phone");
        System.out.println(num);
        if(StringUtils.isBlank(num) || num.length() != 11){
            return MessageUtil.error(400,"手机号码错误");
        }
        String code = createCode();
        JedisUtils.setex(num, EXPIRETIME, code);

        if(ENABLESMS){
            String s = ZhenziSmsUtils.sendSMS(num, code);
            System.out.println(s);
        }else {
            System.out.println("手机验证码:" + code);
        }

        return MessageUtil.success("成功发送验证码，请耐心等待");
    }

    public String createCode(){
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for(int i = 0; i <= 5; i++){
            stringBuffer.append(random.nextInt(10));
        }

        String code = stringBuffer.toString();

        return code;
    }

}
