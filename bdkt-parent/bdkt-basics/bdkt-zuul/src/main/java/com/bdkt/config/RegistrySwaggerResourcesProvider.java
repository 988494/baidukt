package com.bdkt.config;

import com.bdkt.util.ServiceName;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * zuul整合swagger
 */
@Component
@Primary//这个注解必须有
public class RegistrySwaggerResourcesProvider implements SwaggerResourcesProvider {
    private final RouteLocator routeLocator;

    @ApolloConfig
    private Config config;

    public RegistrySwaggerResourcesProvider(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<Route> routes = routeLocator.getRoutes();
        routes.forEach(route -> {
            //授权不维护到swagger 认证服务 不维护swagger
            if (!StringUtils.contains(route.getId(), ServiceName.BDKT_AUTH_SERVICE)){
                /**
                 * zuul:
                 *   routes:
                 *     api-a:
                 *       path: /api-weixin/**
                 *       serviceId: bdkt-service-weixin
                 *  route.getId()指的是api-a
                 *  this.fullPath = prefix + path; 如： /api-weixin/**， route.getFullPath().replace("**", "v2/api-docs")，则为/api-weixin/v2/api-docs
                 *  route.getFullPath()指的是/api-weixin/**
                 */
                resources.add(swaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs")));
            }
        });
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}
