package com.wipro.UserAuthService.jwt;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
	UserDetailsService userDetailsService();
}
