package com.github.seguri.spring_oauth2.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

  @Value("${resourceServerUri}")
  private String resourceServerUri;

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  @Bean
  public RouteLocator myRoutes(RouteLocatorBuilder builder) {
    return builder
        .routes()
        .route(
            "resource-server",
            r ->
                r.path("/profile/**", "/metric/**", "/advice/**")
                    .filters(f -> f.stripPrefix(0))
                    .uri(resourceServerUri))
        .route(
            "httpbin",
            r ->
                r.path("/anything")
                    .filters(f -> f.removeRequestHeader("Authorization"))
                    .uri("http://httpbin.org:80"))
        .build();
  }
}
