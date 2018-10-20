package com.fsdm.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 房上的猫
 * @create 2018-09-12 20:05
 * @博客地址: https://www.cnblogs.com/lsy131479/
 **/
//表示此为配置类，优先加载
@Configuration
public class RibbonConfig {
    //加入到ioc容器
    @Bean
    //结合ribbon开启了负载均衡功能
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
