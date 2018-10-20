package com.fsdm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 房上的猫
 * @create 2018-10-10 16:38
 * @博客地址: https://www.cnblogs.com/lsy131479/
 **/

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/hi")
    public String hi(){
        return "I'm forezp";
    }
}
