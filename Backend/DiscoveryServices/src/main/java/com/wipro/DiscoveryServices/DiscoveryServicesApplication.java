package com.wipro.DiscoveryServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DiscoveryServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryServicesApplication.class, args);
	}

}
