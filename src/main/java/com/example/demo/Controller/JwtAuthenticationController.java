package com.example.demo.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtRequestFilter;
import com.example.demo.config.JwtTokenUtil;
import com.example.demo.config.PreFilter;
import com.example.demo.config.WebSecurityConfig;
import com.example.demo.model.ErrorResponse;
import com.example.demo.model.JwtRequest;
import com.example.demo.model.JwtResponse;
import com.example.demo.service.JwtUserDetailsService;
import com.example.demo.test.IUserService;
import com.example.demo.test.Users;

@Controller
@CrossOrigin
public class JwtAuthenticationController {
	
	@Autowired
	PreFilter preFilter;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private IUserService userService;

	private String Usertype;
	ErrorResponse Error;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		List<Users> User = (List<Users>) userService.findAll();
		Boolean authenticated = false;
		for (Users obj : User) {

			if (obj.getUsername().equals(authenticationRequest.getUsername())
					&& obj.getPassword().equals(authenticationRequest.getPassword())) {
				this.Usertype = obj.getUsertype();
				authenticated = true;
				break;
			}
		}

		if (authenticated == true) {
			userDetailsService.setUser(authenticationRequest.getUsername());
			userDetailsService.setPassword((authenticationRequest.getPassword()));
//	System.out.println(authenticationRequest.getUsername() + authenticationRequest.getPassword() + userDetailsService.getPassword() + userDetailsService.getUser());
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

			final String token = jwtTokenUtil.generateToken(userDetails, Usertype);
			// ResponseEntity.ok(new JwtResponse(token))
			return ResponseEntity.ok(new JwtResponse(token));
		} else {
			return Error.Invalid();
		}

	}

	private void authenticate(String username, String password) throws Exception {
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

	}

	@GetMapping(value = "/validateToken")
	public ResponseEntity<?> ValidateToken() {
		// jwtRequestFilter.doFilterInternal();

		return ResponseEntity.ok("kkk");
	}

	@GetMapping(value = "/get")
	public ResponseEntity<?> test(@RequestHeader String Authorization) throws ServletException, IOException {

		
		return ResponseEntity.ok(Usertype);
	}

}
