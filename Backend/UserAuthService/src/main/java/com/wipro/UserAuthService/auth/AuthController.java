package com.wipro.UserAuthService.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.UserAuthService.dto.AuthenticationRequest;
import com.wipro.UserAuthService.dto.AuthenticationResponse;
import com.wipro.UserAuthService.dto.SignupRequest;
import com.wipro.UserAuthService.dto.UserDto;
import com.wipro.UserAuthService.entities.User;
import com.wipro.UserAuthService.jwt.UserService;
import com.wipro.UserAuthService.repositories.UserRepository;
import com.wipro.UserAuthService.service.auth.AuthService;
import com.wipro.UserAuthService.utils.JwtUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
 
public class AuthController {
private final AuthService authService;
	
	private final UserRepository userRepository;
	
	private final JwtUtil jwtUtil;
	private final UserService userService;
	
	private final AuthenticationManager authenticationManager;
		

	
	
	@PostMapping("/signup")
	public ResponseEntity<?> signupUser(@Valid @RequestBody SignupRequest signupRequest)
	{
	
		if(authService.hasUserWithEmail(signupRequest.getEmail()))
		{
			
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("user already exist with this email");
		}
		UserDto createdUserDto=authService.signupUser(signupRequest);
		if(createdUserDto==null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not created");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
	}
	
	@PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect password or email!");
        }
 
        Optional<User> optionalUser = userRepository.findFirstByEmail(authenticationRequest.getEmail());
        
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            
            // Create claims with role information
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", user.getUserRole().name());
            claims.put("userRole", user.getUserRole().name());
            
            // Generate token with claims
            final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
            final String jwtToken = jwtUtil.generateToken(userDetails);
            
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setJwt(jwtToken);
            authenticationResponse.setUserId(user.getId());
            authenticationResponse.setUserRole(user.getUserRole());
            
            return authenticationResponse;
        }
        
        throw new BadCredentialsException("User not found");
    }
	
	

	
//	@PostMapping("/login")
//	public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest)
//	{
//		try  //that user with that email and pass exist in our db 
//		{
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
//					(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
//		}catch (BadCredentialsException e) {
//			throw new BadCredentialsException("Incorrect password or email!");
//		}
//		    final UserDetails userDetails=userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
//			Optional<User>optionalUser=	userRepository.findFirstByEmail(authenticationRequest.getEmail());
//			final String jwtToken=jwtUtil.generateToken(userDetails);
//			AuthenticationResponse authenticationResponse=new AuthenticationResponse();
//			if(optionalUser.isPresent())
//			{
//				authenticationResponse.setJwt(jwtToken);
//				authenticationResponse.setUserId(optionalUser.get().getId());
//				authenticationResponse.setUserRole(optionalUser.get().getUserRole());
//			}
//			return  authenticationResponse;
//	}

}
