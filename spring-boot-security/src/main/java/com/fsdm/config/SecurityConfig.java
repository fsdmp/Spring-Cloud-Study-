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

    /**
     * 才知道,在带参方法上加上@Autowired spring容器会注入方法参数的实例
     */
    @Autowired
    public void ConfigureGlobal(AuthenticationManagerBuilder auth) {
        try {
            /**
             * 创建一个认证用户的信息
             *      用户名：fsdm
             *      密码：123
             *      角色：USER【自定义】
             * 代码虽少，五脏俱全：
             *      1.应用的每一个请求都需要认证
             *      2.自带生成了一个登陆表单
             *      3.可以用username和password来进行认证
             *      4.用户可以注销
             *      5.阻止了CSRF攻击
             *      6.Session Fixation保护
             *      7.安全Header集成了：
             *          1）HTTP Strict Transport Security for secure request【用于安全请求的HTTP严格传输安全性】
             *          2）X-Content-Type-Options integration【X-Content-Type-Options集成】
             *          3）Cache Control【缓存控制】
             *          4）X-XXS-Protection integration【X-XXS-Protection集成】
             *          5）XFrame-Options integration to help prevent Clickjacking【XFrame-Options集成有助于防止点击劫持】
             *      8.集成了以下Servlet API 的方法：
             *          1）HttpServletRequest # getRemoteUser
             *          2）HttpServletRequest.html # getUserPrincipal
             *          3）HttpServletRequest.html # isUserInRole(java.lang.String)
             *          3）HttpServletRequest.html # login(java.lang.String,java.lang.String)
             *          3）HttpServletRequest.html # logout()
             */
            //auth.inMemoryAuthentication().withUser("fsdm").password("123").roles("USER");

            auth.userDetailsService(userDetailsService1());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Bean
    public UserDetailsService userDetailsService1() {
        /**
         * InMemoryUserDetailsManager 类是将用户信息存放在程序的内存中
         * 程序启动后I nMemoryUserDetailsManager 会在内存中创建用户信息
         */
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("fsdm").password("123").roles("USER").build());
        manager.createUser(User.withUsername("admin").password("123").roles("ADMIN","USER").build());
        return manager;
    }
}
