package com.api.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.api.demo.config.JWTAuthtenticationConfig;
import com.api.demo.config.model.JwtRequest;
import com.api.demo.config.model.JwtResponse;
import com.api.demo.entities.Usuario;
import com.api.demo.repositories.UsuarioRepository;
import com.api.demo.services.UsuarioService;
@RestController
@CrossOrigin
public class JwtAuthenticationController {
	@Autowired
	JWTAuthtenticationConfig jwtAuthtenticationConfig;
	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
	@Autowired
	private UsuarioRepository UsuarioServiceImp;
	@RequestMapping(
			value = "/authenticate", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest, @RequestHeader("APIkey") String APIKey )
			throws Exception {
		System.out.println("********************************************************************");
		System.out.println("authenticationRequest.getUsername():["+authenticationRequest.getUsername()+"]");
		System.out.println("authenticationRequest.getPassword():["+authenticationRequest.getPassword()+"]");
		System.out.println("APIKey:["+APIKey+"]");
		System.out.println("********************************************************************");
		Usuario user = UsuarioServiceImp.findByUsernameANDAPIKey(authenticationRequest.getUsername(), APIKey);
		final UserDetails userDetails = jwtInMemoryUserDetailsService
				//.loadUserByUsername(authenticationRequest.getUsername());
				.loadUserByUsername(user.getId().getLogin());
		//final String token = jwtAuthtenticationConfig.getJWTToken(userDetails.getUsername());
		final String token = jwtAuthtenticationConfig.getJWTToken(user.getId().getLogin());
		System.out.println("********************************************************************");
		System.out.println("token:["+token+"]");
		System.out.println("********************************************************************");
		//return ResponseEntity.ok(new JwtResponse(token, user.getId().getLogin(), user.getPassword()));
		return ResponseEntity.ok(new JwtResponse(token));
	}
}