package com.bdkt;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableSwagger2Doc
@EnableApolloConfig
public class AppGateWay {

	@ApolloConfig
	private Config config;

	public static void main(String[] args) {
		SpringApplication.run(AppGateWay.class, args);
	}

//	// 添加文档来源
//	@Component
//	@Primary
//	class DocumentationConfig implements SwaggerResourcesProvider {
//		@Override
//		public List<SwaggerResource> get() {
//			// 开启监听，配置文件发生改变需要更改
//			config.addChangeListener(new ConfigChangeListener() {
//
//				@Override
//				public void onChange(ConfigChangeEvent changeEvent) {
//					get();
//				}
//			});
//			return resources();
//		}
//
//		/**
//		 * 从阿波罗服务器中获取resources
//		 *
//		 * @return
//		 */
//		private List<SwaggerResource> resources() {
//
//			List resources = new ArrayList<>();
//			// 网关使用服务别名获取远程服务的SwaggerApi
//			String swaggerDocJson = swaggerDocument();
//			JSONArray jsonArray = JSONArray.parseArray(swaggerDocJson);
//			for (Object object : jsonArray) {
//				JSONObject jsonObject = (JSONObject) object;
//				String name = jsonObject.getString("name");
//				String location = jsonObject.getString("location");
//				String version = jsonObject.getString("version");
//				resources.add(swaggerResource(name, location, version));
//			}
//			return resources;
//		}
//
//		/**
//		 * 获取swaggerDocument配置
//		 *
//		 * @return
//		 */
//		private String swaggerDocument() {
//			String property = config.getProperty("mayikt.zuul.swaggerDocument", "");
//			return property;
//		}
//	}
}