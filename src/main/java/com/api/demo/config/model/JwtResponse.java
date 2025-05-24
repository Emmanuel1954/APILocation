package com.api.demo.config.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de autenticación con el token JWT generado.")
public class JwtResponse {
	@Schema(description = "Token de autenticación JWT. Incluye el prefijo 'Bearer '.", example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsImV4cCI6MTY3ODU2NzA5OH0....")
	private String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	// Getter
	public String getJwttoken() { return jwttoken; }
	public void setJwttoken(String jwttoken) { this.jwttoken = jwttoken; } // Añadir setter también
}