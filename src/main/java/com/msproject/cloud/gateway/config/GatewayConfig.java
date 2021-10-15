package com.msproject.cloud.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
	    return builder.routes()
	    		.route(p -> p
	    				.path("/users/**")
	    				.uri("http://localhost:9002")
	    			  )
	    		.route(p -> p
	    				.path("/users/")
	    				.uri("http://localhost:9002")
	    			  )
	    		.route(p -> p
	    				.path("/departments/**")
	    				.uri("http://localhost:9001")
	    			  )
	    		.route(p -> p
	    				.path("/departments/")
	    				.uri("http://localhost:9001")
	    			  )
	    		.build();
	}
	
}
