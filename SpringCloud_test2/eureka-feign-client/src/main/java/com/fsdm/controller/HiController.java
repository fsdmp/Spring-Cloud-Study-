package com.fsdm.controller;

import com.fsdm.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 房上的猫
 * @create 2018-09-19 14:57
 * @博客地址: https://www.cnblogs.com/lsy131479/
 **/

@RestController
public class HiController {
    @Autowired
    HiService hiService;

    @GetMapping("/hi")
    public String sayHi(@RequestParam(defaultValue = "fsdm",required = false)String name){
        return hiService.sayHi(name);
    }
}
