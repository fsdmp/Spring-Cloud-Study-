package com.fsdm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableEurekaClient//客户端
@EnableHystrix//熔断
@EnableHystrixDashboard//开启HystrixDashboard功能。访问hystrix.stream==>熔断器数据指数。  访问hystrix 填入地址，延迟指数，参数==》数据指标
public class EurekaRibbonClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaRibbonClientApplication.class, args);
	}
}
