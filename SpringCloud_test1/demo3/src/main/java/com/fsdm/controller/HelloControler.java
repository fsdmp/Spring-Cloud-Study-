package com.fsdm.controller;


import com.fsdm.serivice.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 房上的猫
 * @create 2018-09-02 16:14
 * @博客地址: https://www.cnblogs.com/lsy131479/
 **/

@RestController
public class HelloControler {

    @Autowired
    HelloService helloService;

    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return helloService.hiService( name );
    }

}
