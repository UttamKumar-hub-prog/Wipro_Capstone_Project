package com.wipro.AuditService.services;

import java.util.List;

import com.wipro.AuditService.entites.AuditLog;

public interface AuditLogService {

	AuditLog auditlogEvent(AuditLog auditLog);

	List<AuditLog> getAllLogs();

}
