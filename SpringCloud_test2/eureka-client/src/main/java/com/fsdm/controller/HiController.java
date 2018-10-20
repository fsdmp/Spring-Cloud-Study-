package com.fsdm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 房上的猫
 * @create 2018-09-12 15:50
 * @博客地址: https://www.cnblogs.com/lsy131479/
 **/

@RestController
public class HiController {
    //向配置文件读取配置端口信息
    @Value("${server.port}")
    String port;

    //表明是一个Get请求，请求地址映射为hi
    @GetMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi " + name + " i am from port:" + port;
    }
}
