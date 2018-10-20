package com.fsdm.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 房上的猫
 * @create 2018-09-26 16:18
 * @博客地址: https://www.cnblogs.com/lsy131479/
 * <p>
 * zuul 自定义过滤器
 * 之前的所有功能（什么熔断啊之类的）其实都是过滤器应用（作用和种类）
 * 个人测试结果：
 *      此自定义过滤器PRE时将在熔断之前执行
 **/

@Component
public class MyFilter extends ZuulFilter {
    /**
     * 按类型对过滤器进行分类。五种：
     *      Zuul中的标准类型是用于路由前过滤的“pre”，
     *      用于路由到源的“route”，
     *      用于路由后过滤器的“post”，
     *      用于错误处理的“error”。
     *      还支持静态响应的“static”类型
     *
     *  可设置FilterConstants接口中的常量值
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 设置过滤顺序
     * 返回int类型值，值越小，越早执行该过滤器
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 设置该过滤器是否过滤逻辑（是否执行run()方法）
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        //当前上下文相应状态
        return ctx.sendZuulResponse();
    }

    /**
     * 具体的过滤的逻辑
     */
    @Override
    public Object run() {
        /*
         RequestContext 当前请求上下文
         getCurrentContext()获取当前上下文
         */
        RequestContext context = RequestContext.getCurrentContext();
        /*
            request作用域实例
         */
        HttpServletRequest request = context.getRequest();
        //是否存在 token 参数
        String token = request.getParameter("token");
        if (null==token){
            /*
                后续是否进行路由
                设为false代表的意思是,这个请求最终不会被zuul转发到后端服务器,
                但是如果当前Filter后面还存在其他Filter,那么其他Filter仍然会被调用到,
             */
            context.setSendZuulResponse(false);
            //设置响应 状态码
            context.setResponseStatusCode(401);

            try {
                //打回页面 参数
                context.getResponse().getWriter().write("token is empty");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
