package com;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 微服务服务实现
 */
@SpringBootApplication
@EnableEurekaClient
@EnableApolloConfig
@ComponentScan(basePackages = {"com.bdkt.weixin.*","com.bdkt.core.utils"})
public class AppWeixin {

	public static void main(String[] args) {
		SpringApplication.run(AppWeixin.class, args);
	}
}
