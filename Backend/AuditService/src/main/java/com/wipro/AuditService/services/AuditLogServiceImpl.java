package com.wipro.AuditService.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.AuditService.entites.AuditLog;
import com.wipro.AuditService.repositorys.AuditLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {
	private final AuditLogRepository auditLogRepository;

	@Override
	public AuditLog auditlogEvent(AuditLog auditLog) {
		return auditLogRepository.save(auditLog);
	}

	@Override
	public List<AuditLog> getAllLogs() {
		return auditLogRepository.findAll();
	}

}
