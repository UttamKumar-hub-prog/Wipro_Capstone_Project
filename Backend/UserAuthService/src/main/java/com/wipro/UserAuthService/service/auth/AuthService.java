package com.wipro.UserAuthService.service.auth;

import com.wipro.UserAuthService.dto.SignupRequest;
import com.wipro.UserAuthService.dto.UserDto;

public interface AuthService {
	UserDto signupUser(SignupRequest signupRequest);
	boolean hasUserWithEmail(String email);
}