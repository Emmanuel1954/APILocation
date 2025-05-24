package com.api.demo.entities;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Schema(description = "Identificador compuesto para un usuario, combinando el ID de la persona y el login.")
public class UsuarioId implements Serializable {
    @Column(name = "idpersona")
    @Schema(description = "ID de la persona a la que el usuario está asociado.", example = "1", required = true)
    private Long idpersona;

    @Schema(description = "Nombre de usuario (login) único.", example = "juanperez", required = true)
    private String login;

    // --- Constructores, equals y hashCode ---
    public UsuarioId() {}
    public UsuarioId(Long idpersona, String login) {
        this.idpersona = idpersona;
        this.login = login;
    }
    public Long getIdpersona() { return idpersona; }
    public void setIdpersona(Long idpersona) { this.idpersona = idpersona; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioId usuarioId = (UsuarioId) o;
        return Objects.equals(idpersona, usuarioId.idpersona) && Objects.equals(login, usuarioId.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idpersona, login);
    }
}