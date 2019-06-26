package com.bdkt.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

/**
 * 将Spring Boot升级到2.0，Spring Cloud升级到Finchley.SR2时，Eureka注册就报错了
 * CSRF保护默认是开启的，可以禁用掉即可
 * 如果需要把其他服务注册到该注册中心上，则需要设置这个
 */
@Component
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        super.configure(http);
    }
}
