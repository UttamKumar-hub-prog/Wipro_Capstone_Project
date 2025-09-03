package com.wipro.AccountService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.AccountService.dto.CustomerDTO;

@FeignClient(name = "CUSTOMER-SERVICE", path = "/customers")
public interface CustomerClient {
	@GetMapping("/{id}")
    CustomerDTO getCustomerById(@PathVariable("id") Long id);
}
