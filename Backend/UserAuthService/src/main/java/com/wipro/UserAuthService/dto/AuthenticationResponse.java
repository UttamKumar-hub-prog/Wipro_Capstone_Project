package com.wipro.UserAuthService.dto;

import com.wipro.UserAuthService.enums.UserRole;

import lombok.Data;

@Data
public class AuthenticationResponse {
	private String jwt;
	private Long userId;
	private UserRole userRole;
	 

}
