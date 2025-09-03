package com.wipro.PaymentService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wipro.PaymentService.dto.AccountDTO;

@FeignClient(name = "ACCOUNT-SERVICE", path = "/accounts")
public interface AccountClient {

	@GetMapping("/{id}")
	AccountDTO getAccountDetailsById(@PathVariable("id") Long id);
	
	@PutMapping("/{id}")
	AccountDTO updateAccount(@PathVariable("id") Long id, @RequestBody AccountDTO account);

}
