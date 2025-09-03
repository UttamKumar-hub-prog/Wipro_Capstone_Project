package com.wipro.PaymentService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wipro.PaymentService.dto.AuditLogDTO;

@FeignClient(name="AUDIT-SERVICE", path="/audits")
public interface AuditLogClient {
	
	@PostMapping("/log")
	AuditLogDTO log(@RequestBody AuditLogDTO auditLogDTO);

}
