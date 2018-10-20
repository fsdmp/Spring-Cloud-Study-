package com.fsdm.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 房上的猫
 * @create 2018-10-18 17:25
 * @博客地址: https://www.cnblogs.com/lsy131479/
 **/

@RestController
public class HiController {

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String hi(String name) {
        return "hi" + name + ",i am fsdm and from port" + port;
    }

    public String hiError(String name) {
        return "hi" + name + ",sorry error!";
    }
}
