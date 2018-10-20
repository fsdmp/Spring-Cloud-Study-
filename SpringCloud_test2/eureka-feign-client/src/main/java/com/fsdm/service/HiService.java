package com.fsdm.service;

import com.fsdm.EurekaClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 房上的猫
 * @create 2018-09-19 14:53
 * @博客地址: https://www.cnblogs.com/lsy131479/
 **/

@Service
public class HiService {
    @Autowired
    EurekaClientFeign eurekaClientFeign;

    public String sayHi(String name){
        return eurekaClientFeign.sayHiFromClientEureka(name);
    }
}
