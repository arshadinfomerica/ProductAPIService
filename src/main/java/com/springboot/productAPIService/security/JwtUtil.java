package com.springboot.productAPIService.security;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	
	private static String secretKey;
    JwtUtil(){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[32];
        random.nextBytes(key);
        secretKey = Base64.getEncoder().encodeToString(key);
    }

	public String generateToken(String username,List<String> role) {
		
		return Jwts.builder()
				.setSubject(username)
				.claim("role", role)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*10))
				.signWith(getKey(),SignatureAlgorithm.HS256)
				.compact();
		
	}
	
	public Key getKey() {
		byte[] k=Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(k);
		
	}
	
	public Claims extractAllClaims(String token) {
	    return Jwts.parserBuilder()
	            .setSigningKey(getKey())
	            .build()
	            .parseClaimsJws(token)
	            .getBody();
	}
	
	public String extractUsername(String token) {
	    return extractAllClaims(token).getSubject();
	}

	public List<String> extractRoles(String token) {
	    return extractAllClaims(token).get("role", List.class);
	}

	public Date extractExpiration(String token) {
	    return extractAllClaims(token).getExpiration();
	}
	
	public boolean isTokenValid(String token, String username) {
	        return extractUsername(token).equals(username) && !isTokenExpired(token);
	   
	}
	
	public boolean isTokenExpired(String token) {
	    return extractAllClaims(token).getExpiration().before(new Date());
	}




}
