package com.wipro.PaymentService.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditLogDTO {
	private Long id;
    private String serviceName;
    private String action;
    private String status;
    private Long referenceId;
    private Long userId;
    private Long amount;
    private String remarks;
    private LocalDateTime createdAt;
}