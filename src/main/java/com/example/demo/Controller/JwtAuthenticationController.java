package com.example.demo.Controller;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.model.JwtResponse;
import com.example.demo.service.JwtUserDetailsService;
import com.example.demo.test.IUserService;
import com.example.demo.test.Users;

@Controller
@CrossOrigin
public class JwtAuthenticationController {
	
@Autowired
private AuthenticationManager authenticationManager;

@Autowired
private JwtTokenUtil jwtTokenUtil;

@Autowired
private JwtUserDetailsService userDetailsService;

@Autowired
private IUserService userService;

private String Usertype;

@PostMapping(value = "/authenticate")
public String createAuthenticationToken(@RequestBody Users authenticationRequest) throws Exception {
	
	userDetailsService.setUser(authenticationRequest.getUsername());
	
	List<Users> User = (List<Users>) userService.findAll();  
   	
	for(Users obj: User)
	{
		System.out.println(obj.getUsername());
		if(obj.getUsername().equals(authenticationRequest.getUsername()) && obj.getUsertype().equalsIgnoreCase("Admin") )
   	 {
   		 this.Usertype="admin";
   		 
   	 }
   	 else if(obj.getUsername() == authenticationRequest.getUsername() && obj.getUsertype() == "student" )
   	 {
   		 this.Usertype = "student";
   		 
   	 }
	}
	
	
authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
final UserDetails userDetails = userDetailsService
.loadUserByUsername(authenticationRequest.getUsername());
final String token = jwtTokenUtil.generateToken(userDetails);
//ResponseEntity.ok(new JwtResponse(token))
return Usertype;
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



}
