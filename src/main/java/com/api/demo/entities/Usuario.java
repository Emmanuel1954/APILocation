package com.api.demo.entities;
import jakarta.persistence.*;

@Entity
public class Usuario {
    @EmbeddedId
    private UsuarioId id;

    @MapsId("idpersona")
    @OneToOne
    @JoinColumn(name = "idpersona")
    private Persona persona;

    private String password;
    private String apikey;

    // Getters y setters

    public UsuarioId getId() {
        return id;
    }

    public void setId(UsuarioId id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }
}