package com.wipro.ApiGateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.wipro.ApiGateway.utils.JwtUtil;

import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter  extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {
	 private final JwtUtil jwtUtil;

	    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
	        super(Config.class);
	        this.jwtUtil = jwtUtil;
	    }

	    @Override
	    public GatewayFilter apply(Config config) {
	        return (exchange, chain) -> {
	            ServerHttpRequest request = exchange.getRequest();
	            
	            // Skip authentication for auth endpoints
	            if (request.getURI().getPath().contains("/api/auth/")) {
	                return chain.filter(exchange);
	            }
	            
	            // Debug information
	            System.out.println("Processing request for path: " + request.getURI().getPath());
	            
	            // Check if Authorization header exists
	            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
	                System.out.println("No Authorization header found");
	                return onError(exchange, "No Authorization header", HttpStatus.UNAUTHORIZED);
	            }

	            String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
	            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	                System.out.println("Invalid Authorization format: " + authHeader);
	                return onError(exchange, "Invalid Authorization header format", HttpStatus.UNAUTHORIZED);
	            }

	            String token = authHeader.substring(7);
	            System.out.println("Extracted token: " + token.substring(0, Math.min(token.length(), 20)) + "...");
	            
	            // Validate JWT token
	            if (!jwtUtil.validateToken(token)) {
	                System.out.println("Invalid or expired token");
	                return onError(exchange, "Invalid or expired JWT token", HttpStatus.UNAUTHORIZED);
	            }
	            
	            // Extract and print the role for debugging
	            String userRole = jwtUtil.extractRole(token);
	            System.out.println("User role extracted from token: " + userRole);
	            
	            // Check role-based access if required
	            if (config.isRequiresRole()) {
	                if (userRole == null) {
	                    System.out.println("No role found in token");
	                    return onError(exchange, "No role found in token", HttpStatus.FORBIDDEN);
	                }
	                
	                // For role-specific routes
	                if (!isAuthorized(userRole, config.getRequiredRoles())) {
	                    System.out.println("User role " + userRole + " not authorized for " + config.getRequiredRoles());
	                    return onError(exchange, "Insufficient permissions", HttpStatus.FORBIDDEN);
	                }
	                System.out.println("Authorization successful for role: " + userRole);
	            }
	            
	            // Add user info to headers for downstream services
	            ServerHttpRequest modifiedRequest = request.mutate()
	                    .header("X-Auth-User-Email", jwtUtil.extractUsername(token))
	                    .header("X-Auth-User-Role", userRole)
	                    .build();
	            
	            return chain.filter(exchange.mutate().request(modifiedRequest).build());
	        };
	    }

	    private boolean isAuthorized(String userRole, String list) {
	        // Admin can access everything
	        if (userRole.equals("ADMIN")) {
	            return true;
	        }
	        
	        // For USER role, only allow access if the required role is USER
	        return userRole.equals(list);
	    }

	    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus status) {
	        ServerHttpResponse response = exchange.getResponse();
	        System.out.println("Error response: " + message + " - Status: " + status);
	        response.setStatusCode(status);
	        return response.setComplete();
	    }
	    
	    
	    
	    
	    public static class Config {
	        private boolean requiresRole = false;
	        private String requiredRoles;

	        public boolean isRequiresRole() {
	            return requiresRole;
	        }

	        public void setRequiresRole(boolean requiresRole) {
	            this.requiresRole = requiresRole;
	        }

	        public String getRequiredRoles() {
	            return requiredRoles;
	        }

	        // Single role
	        public void setRequiredRole(String requiredRole) {
	            this.requiredRoles = requiredRole;
	            this.requiresRole = true;
	        }

	        
	    }

//	    public static class Config {
//	        private boolean requiresRole = false;
//	        private String requiredRole;
//
//	        public boolean isRequiresRole() {
//	            return requiresRole;
//	        }
//
//	        public void setRequiresRole(boolean requiresRole) {
//	            this.requiresRole = requiresRole;
//	        }
//
//	        public String getRequiredRole() {
//	            return requiredRole;
//	        }
//
//	        public void setRequiredRole(String requiredRole) {
//	            this.requiredRole = requiredRole;
//	            this.requiresRole = true;
//	        }

		 
	    
}
