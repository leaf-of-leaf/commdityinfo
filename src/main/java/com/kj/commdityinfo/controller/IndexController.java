package com.kj.commdityinfo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author kj
 * @Date 2020/4/28 16:20
 * @Version 1.0
 * 测试用Controller
 */
@Controller
public class IndexController {
    @GetMapping("/index")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public String Index(){
        System.out.println("进入/index");
        return "/index";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('admin')")
    public void Admin(HttpServletResponse response) throws Exception{
        System.out.println("进入/admin");
        PrintWriter writer = response.getWriter();
        writer.write("This Controller permitte Admin");
        writer.flush();
        writer.close();
    }
}
