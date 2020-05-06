package com.kj.commdityinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan({"com.kj.commdityinfo.mapper"})
@SpringBootApplication
public class CommdityinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommdityinfoApplication.class, args);
    }
}
