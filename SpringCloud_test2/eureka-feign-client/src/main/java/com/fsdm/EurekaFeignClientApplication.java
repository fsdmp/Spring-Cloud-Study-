package com.fsdm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableEurekaClient
/**
 * 开启包扫描，扫描被@FeignClient修饰的接口  这里是EurekaClientFeign类
 * 源码在FeignClientsRegistrar类中registerDefaultConfiguration（）方法
 */
@EnableFeignClients
@SpringBootApplication
@EnableHystrixDashboard
@EnableHystrix
public class EurekaFeignClientApplication {

    /**
     * feign 是一个伪Java http客户端
     */
    public static void main(String[] args) {

        SpringApplication.run(EurekaFeignClientApplication.class, args);
    }
    /**
     * 默认网络请求为：HttpURLConnection
     * 在工厂pom文件加上feign-httpvlient依赖，feign就会采用HttpClient作为网络请求框架：
         <dependency>
         <groupId>com.netflix.feign</groupId>
         <artifactId>feign-httpclient</artifactId>
         <version>RELEASE</version>
         </dependency>

     * 使用Okhttp作为网络请求框架：
         <dependency>
         <groupId>com.netflix.feign</groupId>
         <artifactId>feign-okhttp</artifactId>
         <version>RELEASE</version>
         </dependency>
     */
}
