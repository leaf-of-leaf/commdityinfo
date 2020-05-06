package com.kj.commdityinfo.service.impl;

import com.kj.commdityinfo.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * @author kj
 * @Date 2020/5/6 14:45
 * @Version 1.0
 */

@Service
public class DemoServiceImpl implements DemoService {

    public void testAop(){
        System.out.println("球进了");
    }

}
