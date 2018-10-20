package com.fsdm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 房上的猫
 * @create 2018-10-09 12:18
 * @博客地址: https://www.cnblogs.com/lsy131479/
 **/
@RestController
@RefreshScope//允许本类在不重启服务的情况下刷新配置
public class TestController {
    @Value("${foo}")
    String foo;

    @RequestMapping(value = "/foo")
    public String hi(){
        return foo;
    }
}
