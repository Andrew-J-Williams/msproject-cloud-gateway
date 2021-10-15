package com.msproject.cloud.gateway.config;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;


@Configuration
public class GatewayConfig {

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
	    return builder.routes()
	    		.route(p -> p
	    				.path("/users/")
	    				.filters(f -> f.circuitBreaker(c -> c.setName("createUserCircuitBreaker").setFallbackUri("/createUserServiceFallBack")))
	    				.uri("lb://USER-SERVICE")
	    			  )
	    		.route(p -> p
	    				.path("/users/**")
	    				.filters(f -> f.circuitBreaker(c -> c.setName("userCircuitBreaker").setFallbackUri("/userServiceFallBack")))
	    				.uri("lb://USER-SERVICE")
	    			  )
	    		.route(p -> p
	    				.path("/departments/")
	    				.filters(f -> f.circuitBreaker(c -> c.setName("createDepartmentCircuitBreaker").setFallbackUri("/createDepartmentServiceFallBack")))
	    				.uri("lb://DEPARTMENT-SERVICE")
	    			  )
	    		.route(p -> p
	    				.path("/departments/**")
	    				.filters(f -> f.circuitBreaker(c -> c.setName("departmentCircuitBreaker").setFallbackUri("/departmentServiceFallBack")))
	    				.uri("lb://DEPARTMENT-SERVICE")
	    			  )
	    		.build();
	}
	
	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory->factory.configureDefault(id-> new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
				.timeLimiterConfig(TimeLimiterConfig.custom()
						.timeoutDuration(Duration.ofSeconds(2)).build()).build());
	}
}
