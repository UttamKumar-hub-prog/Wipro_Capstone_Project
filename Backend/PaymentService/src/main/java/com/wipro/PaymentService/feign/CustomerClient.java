package com.wipro.PaymentService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.PaymentService.dto.CustomerDTO;

@FeignClient(name="CUSTOMER-SERVICE", path="/customers")
public interface CustomerClient {
	@GetMapping("/{id}")
	CustomerDTO getCustomerById(@PathVariable("id") Long id);

}
