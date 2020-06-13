package com.kj.commdityinfo.controller;

import com.kj.commdityinfo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kj
 * @Date 2020/5/6 14:47
 * @Version 1.0
 */

//@Controller
public class DemoController {

//    @Autowired
//    private DemoService demoService;
//
//    @ResponseBody
//    @GetMapping("/demo")
//    public String demo(NativeWebRequest nativeWebRequest){
//        demoService.testAop();
//        return "Hello";
//    }
}
