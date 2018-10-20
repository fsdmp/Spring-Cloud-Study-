package com.fsdm.hystrix;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author 房上的猫
 * @create 2018-09-26 15:50
 * @博客地址: https://www.cnblogs.com/lsy131479/
 * <p>
 * zuul 应用 负载均衡 熔断器功能
 **/

//普通pojo实例化到spring容器中
@Component
public class MyFallbackProvider implements ZuulFallbackProvider {
    /**
     * 用于指定熔断功能应用于哪些路由服务
     * 如果需要所有的路由服务都加熔断功能，return "*"  即可
     *
     * 这里如果需要将自己维护的服务列表应用于熔断器，需要写的是自定义的熔断名。如：hiapi-v1
     */
    @Override
    public String getRoute() {
        System.out.println("xxxxxxxxxxxxx");
        return "EUERKA-CLIENT";
    }

    /**
     * 进入熔断功能时执行的逻辑
     *个人测试：
     * 执行熔断逻辑：就近原则。如这里访问feign消费来消费EUERKA-CLIENT，并且其配置了熔断器。。将执行feign的熔断机制，此处不会执行
     */
    @Override
    public ClientHttpResponse fallbackResponse() {
        System.out.println("==============");
        return new ClientHttpResponse() {
            /**
             * 返回响应的HTTP状态代码
             * @return   HttpStatus枚举值
             */
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            /**
             * 将响应的HTTP状态代码作为整数返回
             */
            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            /**
             * 返回响应的HTTP状态文本。
             */
            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            /**
             * 关闭此响应，释放所有创建的资源。
             */
            @Override
            public void close() {

            }

            /**
             * 将消息正文作为输入流返回。
             */
            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("oooops!error,i'm the fallback.".getBytes());
            }

            /**
             * 返回此消息的标题/形式
             */
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                //json形式返回
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
