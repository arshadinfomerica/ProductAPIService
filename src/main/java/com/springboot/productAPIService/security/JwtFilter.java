package com.springboot.productAPIService.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springboot.productAPIService.service.MyUsersDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private MyUsersDetailsService userDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header=request.getHeader("Authorization");
		String username=null;
		String token=null;
		if(header!=null && header.startsWith("Bearer ")) {
			token =header.substring(7);
			username=jwtUtil.extractUsername(token);
			
		}
		 if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails = userDetailService.loadUserByUsername(username);
	            if (userDetails != null) {
	                List<String> roles = jwtUtil.extractRoles(token);
	                List<SimpleGrantedAuthority> authorities = roles.stream()
	                        .map(role -> new SimpleGrantedAuthority(role))
	                        .toList();

	                if (jwtUtil.isTokenValid(token, userDetails.getUsername())) {
	                    UsernamePasswordAuthenticationToken authToken =
	                            new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
	                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                    SecurityContextHolder.getContext().setAuthentication(authToken);
	                }
	            }
		 }
		 filterChain.doFilter(request, response);
		
	}

}
