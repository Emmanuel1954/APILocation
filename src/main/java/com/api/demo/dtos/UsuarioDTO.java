package com.api.demo.dtos;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de transferencia de datos para actualizar la contraseña de un usuario.")
public class UsuarioDTO {
    @Schema(description = "Nueva contraseña del usuario.", example = "nuevaContrasenaSegura123", required = true)
    private String password;

    // --- Getters y setters ---
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}