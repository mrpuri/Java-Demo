package com.example.demo.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;

@Component
@Order(1)
public class PreFilter implements Filter {
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 HttpServletRequest req = (HttpServletRequest) request;
	        
         req.getRequestURI();
         System.out.println("in pre filter ");
 
         
         
       chain.doFilter(request, response);
      	
	}

	
	public void authorizationFilter(String requestTokenHeader)
			throws ServletException, IOException {

		String usertype = null;
		String jwtToken = null;

// JWT Token is in the form "Bearer token". Remove Bearer word and get
// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

			jwtToken = requestTokenHeader.substring(7);

			try {
				usertype = jwtTokenUtil.getUsertypeFromToken(jwtToken);

			} catch (IllegalArgumentException e) {

				System.out.println("Unable to get JWT Token");

			} catch (ExpiredJwtException e) {

				System.out.println("JWT Token has expired");
			}

		} else {

			System.out.println("JWT Token does not begin with String");
		}


		if (usertype == "Admin" ) {
			System.out.println("he is admin");
			
			
		}
		else
		{
			System.out.println("he is Student");
		}
		
		
	}
  
}