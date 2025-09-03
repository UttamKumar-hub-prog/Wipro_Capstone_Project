package com.wipro.ApiGateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import com.wipro.ApiGateway.filter.JwtAuthenticationFilter;

@EnableMethodSecurity
@Configuration
public class GatewayConfig {
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				// Auth service (public)
				.route("userauth-service-route", r -> r.path("/auth/**").uri("lb://USER-AUTHENTICATION-SERVICE"))

				// Customer service
				.route("customer-service-route", r -> r.path("/customers/**")
//						.filters(f -> {
//					JwtAuthenticationFilter.Config config = new JwtAuthenticationFilter.Config();
//					// Optional: set requiredRole if you want gateway-level role enforcement
////					config.setRequiredRole("USER");
////					config.setRequiredRole(List.of("ADMIN", "USER"));
//					return f.filter(jwtAuthenticationFilter.apply(config));
//				})
						.uri("lb://CUSTOMER-SERVICE"))

				// Payment service
				.route("payment-service-route", r -> r.path("/payments/**")
//						.filters(f -> {
//					JwtAuthenticationFilter.Config config = new JwtAuthenticationFilter.Config();
////					config.setRequiredRole("USER");
//					return f.filter(jwtAuthenticationFilter.apply(config));
//				})
						.uri("lb://PAYMENT-SERVICE"))

				// Account service
				.route("account-service-route", r -> r.path("/accounts/**")
//						.filters(f -> {
//					JwtAuthenticationFilter.Config config = new JwtAuthenticationFilter.Config();
////					config.setRequiredRole("ADMIN");
////					config.setRequiredRole(List.of("ADMIN", "USER"));
//					return f.filter(jwtAuthenticationFilter.apply(config));
//				})
						.uri("lb://ACCOUNT-SERVICE"))

				// Notification service
				.route("notification-service-route", r -> r.path("/notifications/**")
//						.filters(f -> {
//					JwtAuthenticationFilter.Config config = new JwtAuthenticationFilter.Config();
//					return f.filter(jwtAuthenticationFilter.apply(config));
//				})
						.uri("lb://NOTIFICATION-SERVICE"))

				// Audit service
				.route("audit-service-route", r -> r.path("/audits/**")
//						.filters(f -> {
//					JwtAuthenticationFilter.Config config = new JwtAuthenticationFilter.Config();
////					config.setRequiredRole("ADMIN");
//					return f.filter(jwtAuthenticationFilter.apply(config));
//				})
						.uri("lb://AUDIT-SERVICE"))

				.route("feedback-service-route", r -> r.path("/feedbacks/**")
//						.filters(f -> {
//					JwtAuthenticationFilter.Config config = new JwtAuthenticationFilter.Config();
////					config.setRequiredRole("ADMIN");
//					return f.filter(jwtAuthenticationFilter.apply(config));
//				})
						.uri("lb://FEEDBACK-SERVICE"))

				.build();
	}
}
