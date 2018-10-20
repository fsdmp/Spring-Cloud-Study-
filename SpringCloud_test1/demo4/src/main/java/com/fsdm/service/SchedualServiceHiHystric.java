package com.fsdm.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author 房上的猫
 * @create 2018-09-03 17:12
 * @博客地址: https://www.cnblogs.com/lsy131479/
 **/
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}
