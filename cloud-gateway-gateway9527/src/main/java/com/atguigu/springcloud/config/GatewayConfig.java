package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr. Hao
 * @date 2021-07-26   14:43
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {

        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_payment1",
                r -> r.path("/guonei")
                        .uri("http://news.baidu.com/guonei"))
                .build();
        routes.route("path_route_payment2",
                r -> r.path("/guoji")
                        .uri("http://news.baidu.com/guoji"))
                .build();

        return routes.build();
    }
}
