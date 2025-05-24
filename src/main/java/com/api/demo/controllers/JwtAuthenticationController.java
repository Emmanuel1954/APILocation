package com.api.demo.controllers;

import com.api.demo.config.JWTAuthtenticationConfig;
import com.api.demo.config.model.JwtRequest;
import com.api.demo.config.model.JwtResponse;
import com.api.demo.entities.Usuario;
import com.api.demo.repositories.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Tag(name = "Autenticación", description = "Gestión de autenticación de usuarios y generación de tokens JWT.")
public class JwtAuthenticationController {

	@Autowired
	JWTAuthtenticationConfig jwtAuthtenticationConfig;
	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
	@Autowired
	private UsuarioRepository UsuarioServiceImp; // Esto parece un nombre de repo/service, no un service

	@Operation(summary = "Autenticar usuario y obtener token JWT",
			description = "Permite a un usuario autenticarse con nombre de usuario, contraseña y API Key, y recibir un token JWT para acceder a los recursos protegidos. **¡No se requiere token JWT para este endpoint!**")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Autenticación exitosa, token JWT retornado",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = JwtResponse.class))),
			@ApiResponse(responseCode = "401", description = "Credenciales inválidas (usuario, contraseña o API Key)", content = @Content)
	})
	@RequestMapping(
			value = "/authenticate",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> createAuthenticationToken(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Credenciales del usuario (username, password).", required = true,
					content = @Content(schema = @Schema(implementation = JwtRequest.class)))
			@RequestBody JwtRequest authenticationRequest,
			@Parameter(description = "API Key para la autenticación.", required = true, example = "su_api_key_aqui")
			@RequestHeader("APIkey") String APIKey) throws Exception {

		Usuario user = UsuarioServiceImp.findByUsernameANDAPIKey(authenticationRequest.getUsername(), APIKey);

		if (user == null || !authenticationRequest.getPassword().equals(user.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username, password or API Key.");
		}

		final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(user.getId().getLogin());
		final String token = jwtAuthtenticationConfig.getJWTToken(user.getId().getLogin());

		return ResponseEntity.ok(new JwtResponse(token));
	}
}