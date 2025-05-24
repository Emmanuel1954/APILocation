package com.api.demo.config.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud de autenticación de usuario.")
public class JwtRequest {
	@Schema(description = "Nombre de usuario para el login.", example = "testuser", required = true)
	private String username;
	@Schema(description = "Contraseña del usuario.", example = "password123", required = true)
	private String password;

	// Getters y setters
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
}