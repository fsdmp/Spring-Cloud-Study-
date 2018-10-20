package com.fsdm.controller;

import com.fsdm.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 房上的猫
 * @create 2018-09-12 20:11
 * @博客地址: https://www.cnblogs.com/lsy131479/
 **/

@RestController
public class RibbonController {
    @Autowired
    RibbonService ribbonService;

    /**
     * defaultValue 默认值
     * required 是否必须传入此参数
     */
    @RequestMapping("/hi")
    public String hi(@RequestParam(required = false,defaultValue = "fsdm") String name){
        return ribbonService.hi(name);
    }

    //LoadBalancerClient可以获取负载均衡的服务器提供者的实例信息
    @Autowired
    private LoadBalancerClient loadBalancer;

    @RequestMapping("/testRibbon")
    public String testRibbon(){
        //获取EUERKA-CLIENT 客户端的实例信息
        ServiceInstance instance = loadBalancer.choose("EUERKA-CLIENT");
        return instance.getHost()+":"+instance.getPort();
    }
}
