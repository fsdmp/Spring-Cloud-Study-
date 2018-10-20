package com.fsdm.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author 房上的猫
 * @create 2018-09-12 20:08
 * @博客地址: https://www.cnblogs.com/lsy131479/
 **/

@Service
public class RibbonService {
    @Autowired
    RestTemplate restTemplate;

    /**
     * 开启熔断
     * fallback逻辑  最好返回一些静态字符串，不需要处理复杂的逻辑，也不需要远程调度其他服务，，以做到方便执行快速失败
     * 这里使用的是fallbackMethod   回调方法（本类）
     * <p>
     * hiError对应hiError()
     * 工作原理：
     *      根据生产者的相应状态，如果未响应或者不可用/错误相应
     *      开启熔断器，进入fallbackMethod逻辑
     *      打开了熔断器，之后的请求就会直接执行fallbackMethod的逻辑
     * 好处：
     *      通过快速失败，请求能够i有得到及时处理，线程不再阻塞
     */

    @HystrixCommand(fallbackMethod = "hiError")
    public String hi(String name) {
        //跨项目通信
        return restTemplate.getForObject("http://EUERKA-CLIENT/hi?name=" + name, String.class);
    }

    public String hiError(String name) {
        return "hi," + name + ",sorry error!";
    }

}


