package com.fsdm;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import zipkin.server.EnableZipkinServer;
import zipkin.storage.mysql.MySQLStorage;

import javax.sql.DataSource;

@SpringBootApplication
@EnableZipkinServer//开启zipkin server功能
@EnableEurekaClient
public class ZipkinServerApplication {

	/**
	 * 将MySQLStorage注入到spring容器，以待使用
	 * MySQLStorage：mysql存储
	 *
	 * @Qualifier:
	 * 		当自动装配时，此注解可用作字段或参数作为候选bean的限定符。它还可以用于注解其他自定义注解，然后可以将其用作限定符
	 *
	 * @Primary:
	 * 		表示当多个候选有资格自动装配单值依赖时，应优先考虑的bean。如果候选者中只存在一个bean，则它将是自动装配的值
	 * 		消除@Autowired引起的歧义现象   最优先
	 * 		不知道为何，不加这个就不好使   个人猜测可能是底层有封装的MySQLStorage bean，引起了@Autowired的歧义
	 */
	@Bean
	@Primary
	public MySQLStorage mySQLStorage(@Qualifier("dataSource") DataSource dataSource){
		return MySQLStorage.builder().datasource(dataSource).executor(Runnable::run).build();
	}
	public static void main(String[] args) {
		SpringApplication.run(ZipkinServerApplication.class, args);
	}
}
