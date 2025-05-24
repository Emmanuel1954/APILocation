package com.api.demo.entities;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Schema(description = "Representa un usuario con credenciales de acceso a la API, asociado a una persona.")
public class Usuario {
    @EmbeddedId
    @Schema(description = "Identificador compuesto para el usuario (incluye ID de persona y login).")
    private UsuarioId id;

    @MapsId("idpersona")
    @OneToOne
    @JoinColumn(name = "idpersona")
    @Schema(description = "La entidad Persona a la que este usuario está asociado.")
    private Persona persona;

    @Schema(description = "Contraseña del usuario (idealmente hasheada).", example = "hashed_password_example", required = true)
    private String password;

    @Schema(description = "Clave de API única para autenticación alternativa o adicional.", example = "abcd-1234-efgh-5678", required = true)
    private String apikey;

    // --- Getters y Setters ---
    public UsuarioId getId() { return id; }
    public void setId(UsuarioId id) { this.id = id; }
    public Persona getPersona() { return persona; }
    public void setPersona(Persona persona) { this.persona = persona; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getApikey() { return apikey; }
    public void setApikey(String apikey) { this.apikey = apikey; }
}