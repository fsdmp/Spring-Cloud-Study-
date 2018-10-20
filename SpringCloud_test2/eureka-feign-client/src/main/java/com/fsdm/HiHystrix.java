package com.fsdm;

import org.springframework.stereotype.Component;

/**
 * @author 房上的猫
 * @create 2018-09-20 16:02
 * @博客地址: https://www.cnblogs.com/lsy131479/
 * <p>
 * feign 熔断器逻辑处理类 必须实现被@FeignClient注解修饰的接口
 **/

@Component//注入到Ioc容器
public class HiHystrix implements EurekaClientFeign {

    /**
     * 熔断执行的方法
     */
    @Override
    public String sayHiFromClientEureka(String name) {
        return "hi," + name + ",sorry,error!";
    }
}
