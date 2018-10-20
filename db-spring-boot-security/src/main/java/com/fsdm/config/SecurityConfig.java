package com.fsdm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.io.OutputStream;

/**
 * @author 房上的猫
 * @create 2018-10-19 16:01
 * @博客地址: https://www.cnblogs.com/lsy131479/
 **/

@Configuration
@EnableWebSecurity//开启WebSecurity功能
@EnableGlobalMethodSecurity(prePostEnabled = true)
/**
 * 启用全局方法安全性
 * prePostEnabled： 方法级别上的权限验证
 *      Spring Security 的Pre【@PreAuthorize 方法前】 和Post【@PostAuthorize 方法后】 注解是否可用
 * securedEnabled：
 *      Spring Security 的 @Secured 注解是否可用
 * jsr250Enabled：
 *      Spring Security 堆 JSR-250 的注解是否可用
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 链式编程
         */
        http.authorizeRequests()
                //以css/**开头的资源和/index资源不需要验证，并且外界请求可以访问这些资源
                .antMatchers("/static/**", "/index").permitAll()
                //以/user/**和/blogs/**开头的资源需要验证，并且角色必须是USER
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/blogs/**").hasRole("USER")
                //表单登陆地址是/login，登陆失败地址是/login-error
                .and().formLogin().loginPage("/login").failureUrl("/login-error")
                //异常处理会 重定向到/401页面
                .and().exceptionHandling().accessDeniedPage("/401");
        //登出成功，重定向到首页
        http.logout().logoutSuccessUrl("/");
    }
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void ConfigureGlobal(AuthenticationManagerBuilder auth) {

        try {
            auth.userDetailsService(userDetailsService);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
