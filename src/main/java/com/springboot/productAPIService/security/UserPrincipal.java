package com.springboot.productAPIService.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.productAPIService.entity.UsersDetails;

public class UserPrincipal implements UserDetails{
	
	private UsersDetails usersDetails;
	public UserPrincipal(UsersDetails usersDetails) {
		this.usersDetails=usersDetails;
		
	}

	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 String role = usersDetails.getRoles();
		    return List.of(new SimpleGrantedAuthority("ROLE_"+role.toUpperCase()));
		}

	@Override
	public String getPassword() {
		return usersDetails.getPassword();
	}

	@Override
	public String getUsername() {
		return usersDetails.getUsername();
	}

}
