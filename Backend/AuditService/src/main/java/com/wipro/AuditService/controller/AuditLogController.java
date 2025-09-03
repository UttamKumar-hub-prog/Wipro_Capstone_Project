package com.wipro.AuditService.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.AuditService.entites.AuditLog;
import com.wipro.AuditService.services.AuditLogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/audits")
public class AuditLogController {

	private final AuditLogService auditLogService;

	@PostMapping("/log")
    public ResponseEntity<AuditLog> log(@RequestBody AuditLog auditLog) {
        return ResponseEntity.ok(auditLogService.auditlogEvent(auditLog));
    }
	
	@GetMapping("/getAll")
	  public ResponseEntity<List<AuditLog>> getAll(){
		return ResponseEntity.ok(auditLogService.getAllLogs());
	}

}
