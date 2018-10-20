package com.fsdm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author 房上的猫
 * @create 2018-10-19 15:16
 * @博客地址: https://www.cnblogs.com/lsy131479/
 **/
@Configuration
//网络安全配置适配器
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登陆页面为login.html  url为login   permitAll()全部允许
        http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll();
        //登出页url为logout
        http.logout().logoutUrl("/logout");
        //禁用csrf（跨站请求伪造）
        http.csrf().disable();
        //这些资源访问不需要认证
        http.authorizeRequests().antMatchers("/login.html","/**/**.css","/img/**","/third-party").permitAll();
        http.authorizeRequests().antMatchers("/**").authenticated();
        //开启http基本认证
        http.httpBasic();
    }
}
