package com.api.demo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de transferencia de datos para mostrar información de un usuario.")
public class UsuarioInfoDTO {
    @Schema(description = "Nombre de usuario (login).", example = "juanperez")
    private String login;
    @Schema(description = "Contraseña del usuario (¡Precaución! Para ambientes productivos, este campo no debería exponerse).", example = "password_hashed_value")
    private String password;
    @Schema(description = "API Key del usuario.", example = "abcd-1234-efgh-5678")
    private String apikey;

    public UsuarioInfoDTO(String login, String password, String apikey) {
        this.login = login;
        this.password = password;
        this.apikey = apikey;
    }

    // --- Getters ---
    public String getLogin() { return login; }
    public String getPassword() { return password; }
    public String getApikey() { return apikey; }
}