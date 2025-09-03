package com.wipro.UserAuthService.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.wipro.UserAuthService.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	public String generateToken(UserDetails userDetails)
	{
		return generateToken(new HashMap<>(),userDetails);
	}
	
	
	private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
	    User user = (User) userDetails; 
	    return Jwts.builder()
	            .claims(extraClaims)
	            .claim("role", user.getUserRole().name())  
	            .subject(user.getUsername())
	            .issuedAt(new Date(System.currentTimeMillis()))
	            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 10))
	            .signWith(getSigningKey())
	            .compact();
	}

	
	private SecretKey getSigningKey() {
		byte[] keyBytes=Decoders.BASE64.decode("26c4cbf903245338881de345222de9e59082fbfd9fd4dd1b198dd734a53d89d0");
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	
	public boolean isTokenValid(String token,UserDetails userDetails)
	{
		  final String userName=extractUserName(token);
		return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	
	public String extractUserName(String token) {
		return extractClaim(token,Claims::getSubject);
	}
	public boolean isTokenValid(String token) {
	    return !isTokenExpired(token);  // only checks expiry & signature
	}

	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return  extractClaim(token,Claims::getExpiration);
	}

	
   public<T> T extractClaim(String token,Function<Claims,T> claimsResolvers)
     {
	   final Claims claims=extractAllClaims(token);
	   return claimsResolvers.apply(claims);
     }

  private Claims extractAllClaims(String token) {
	    return Jwts.parser()
	            .verifyWith(getSigningKey())   
	            .build()
	            .parseSignedClaims(token)      
	            .getPayload();
	}




}
