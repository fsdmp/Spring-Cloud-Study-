package com.fsdm.filter;

import com.netflix.zuul.ZuulFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

/**
 * @author 房上的猫
 * @create 2018-10-11 21:21
 * @博客地址: https://www.cnblogs.com/lsy131479/
 **/

@Component
public class LoggerFilter extends ZuulFilter {

    @Autowired
    //链路跟踪的类
    private Tracer tracer;

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 900;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        //在链路数据中添加自定义数据
        tracer.addTag("operator","fsdm");
        /**
         *  获取当前链路的traceId
         * traceId作为链路数据的唯一标识
         */
        System.out.println(tracer.getCurrentSpan().traceIdString());
        return null;
    }
}
