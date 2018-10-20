package com.fsdm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

@SpringBootApplication
@EnableEurekaClient
@EnableZipkinStreamServer//消息对列传输链路i数据
public class ElRabbitmqZipkinServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElRabbitmqZipkinServerApplication.class, args);
	}
}
