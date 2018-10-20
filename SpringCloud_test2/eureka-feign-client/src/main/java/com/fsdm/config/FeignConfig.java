package com.fsdm.config;

import feign.RetryableException;
import feign.Retryer;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author 房上的猫
 * @create 2018-09-19 14:33
 * @博客地址: https://www.cnblogs.com/lsy131479/
 *
 * @Configuration 表明这是一个配置类
 **/

@Configuration
public class FeignConfig {
    /**
     * feign client 默认配置类为 FeignClientsConfiguration
     *                      内有注解@ConditionalOnMissingBean
     *                      表示如果没有注入该类的bean就会默认注入一个bean
     *                      此处会覆盖掉feignRetryer()底层的默认bean（自定义目的）
     */
    @Bean
    public Retryer feignRetryer(){
        /**
         * Retry重试机制：
         * Default(long period, long maxPeriod, int maxAttempts)方法
         * Retryer内部类的构造方法，
         *      period：重试时间间隔
         *      maxPeriod：最大重试时间
         *      maxAttempts：最大重试次数，（默认事是0，即不重试（Retryer.NEVER_RETRY））
         */
        return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1),5);
    }
}
