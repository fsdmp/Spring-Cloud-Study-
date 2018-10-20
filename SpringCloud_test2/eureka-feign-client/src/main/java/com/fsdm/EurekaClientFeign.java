package com.fsdm;

import com.fsdm.config.FeignConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 房上的猫
 * @create 2018-09-19 14:31
 * @博客地址: https://www.cnblogs.com/lsy131479/
 * @FeignClient : 声明一个Feign Client
 * value ：远程调用其他服务的服务名
 * configuration ：配置类
 * fallback：熔断逻辑处理类
 * <p>
 * 在此类中sayHiFromClientEureka（）方法通过feign来调用eureka-client服务的/hi  的API接口
 **/
@FeignClient(value = "EUERKA-CLIENT", configuration = FeignConfig.class, fallback = HiHystrix.class)
public interface EurekaClientFeign {
    @GetMapping(value = "/hi")
    String sayHiFromClientEureka(@RequestParam(value = "name") String name);
}
