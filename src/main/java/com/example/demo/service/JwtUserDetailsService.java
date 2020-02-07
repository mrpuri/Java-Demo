package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	private String User = null;
	private String password = null;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (User.equals(username)) {
			return new User(User, this.password,
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
	
	public void setUser(String User) {
		this.User= User;
	}
	public void setPassword(String password) {
		this.password = bcryptEncoder.encode(password);
	}
	public String getUser() {
		return this.User;
	}
	public String getPassword() {
		return this.password;
	}
}