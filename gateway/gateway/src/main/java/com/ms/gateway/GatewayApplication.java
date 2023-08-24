package com.ms.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	@Bean
	RouteLocator adminRoute(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("ps", rs->rs.path("/admin/**")
				.uri("lb://app-admin"))
				.build();
	}
	
	@Bean
	RouteLocator vendorRoute(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("sc", rs->rs.path("/ms/**")
				.uri("lb://app-vendor"))
				.build();
	}
	@Bean
	RouteLocator customerRoute(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("us", rs->rs.path("/customer/**")
				.uri("lb://app-customer"))
				.build();
	}
	
	@Bean
	RouteLocator foodRoute(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("fd", rs->rs.path("/food/**")
				.uri("lb://app-food"))
				.build();
	}
	@Bean
	RouteLocator cartRoute(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("us", rs->rs.path("/cart/**")
				.uri("lb://app-cart"))
				.build();
	}
}
