package com.api.demo.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Schema(description = "Representa una persona en el sistema de rastreo de ubicaciones.")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la persona.", example = "1")
    private long id;

    @Schema(description = "El primer nombre de la persona.", example = "Juan", required = true)
    private String primerNombre;

    @Schema(description = "El segundo nombre de la persona (opcional).", example = "Andrés")
    private String segundoNombre;

    @Schema(description = "El primer apellido de la persona.", example = "Pérez", required = true)
    private String primerApellido;

    @Schema(description = "El segundo apellido de la persona (opcional).", example = "Gómez")
    private String segundoApellido;

    @Column(unique = true)
    @Schema(description = "Número de identificación único de la persona.", example = "1234567890", required = true)
    private String numeroIdentificacion;

    @Schema(description = "Fecha de nacimiento de la persona (formato YYYY-MM-DD).", example = "1990-05-15", required = true)
    private LocalDate fechaNacimiento;

    @Schema(description = "Edad de la persona en años.", example = "34", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer edad;

    @Schema(description = "Edad clínica de la persona (años, meses, días).", example = "34 años 0 meses 7 días", accessMode = Schema.AccessMode.READ_ONLY)
    private String edadClinica;

    @Schema(description = "Dirección de la ubicación actual de la persona.", example = "Carrera 1 #1-1, Ibagué, Tolima", required = true)
    private String ubicacion;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @Schema(description = "El usuario asociado a esta persona (credenciales de acceso a la API).")
    private Usuario usuario;

    // --- Constructores, Getters y Setters (los mantengo abreviados por brevedad aquí, pero deben estar completos en tu archivo) ---
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getPrimerNombre() { return primerNombre; }
    public void setPrimerNombre(String primerNombre) { this.primerNombre = primerNombre; }
    public String getSegundoNombre() { return segundoNombre; }
    public void setSegundoNombre(String segundoNombre) { this.segundoNombre = segundoNombre; }
    public String getPrimerApellido() { return primerApellido; }
    public void setPrimerApellido(String primerApellido) { this.primerApellido = primerApellido; }
    public String getSegundoApellido() { return segundoApellido; }
    public void setSegundoApellido(String segundoApellido) { this.segundoApellido = segundoApellido; }
    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public void setNumeroIdentificacion(String numeroIdentificacion) { this.numeroIdentificacion = numeroIdentificacion; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }
    public String getEdadClinica() { return edadClinica; }
    public void setEdadClinica(String edadClinica) { this.edadClinica = edadClinica; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public void calcularEdad() {
        if (this.fechaNacimiento != null) {
            LocalDate ahora = LocalDate.now();
            Period periodo = Period.between(this.fechaNacimiento, ahora);
            this.edad = periodo.getYears();
            this.edadClinica = String.format("%d años %d meses %d días", periodo.getYears(), periodo.getMonths(), periodo.getDays());
        }
    }
}