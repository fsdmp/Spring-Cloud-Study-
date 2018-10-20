package com.fsdm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
/**
 * @EnableDiscoveryClient
 *
 * 使用其他的注册中心后，比如zookeeper等，
 * 都可以使用@EnableDiscoveryClient注解，
 * 但是使用@EnableEurekaClient的情景，
 * 就是在服务采用eureka作为注册中心的时候，使用场景比较单一
 */

public class EurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}
}
