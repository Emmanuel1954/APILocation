package com.api.demo.dtos;
import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Objeto de transferencia de datos para crear o actualizar una Persona.")
public class PersonaDTO {
    @Schema(description = "Primer nombre de la persona.", example = "María", required = true)
    private String primerNombre;
    @Schema(description = "Segundo nombre de la persona (opcional).", example = "Isabel")
    private String segundoNombre;
    @Schema(description = "Primer apellido de la persona.", example = "García", required = true)
    private String primerApellido;
    @Schema(description = "Segundo apellido de la persona (opcional).", example = "López")
    private String segundoApellido;
    @Schema(description = "Número de identificación de la persona.", example = "9876543210", required = true)
    private String numeroIdentificacion;
    @Schema(description = "Fecha de nacimiento de la persona en formato YYYY-MM-DD.", example = "1995-11-20", required = true)
    private LocalDate fechaNacimiento;
    @Schema(description = "Dirección de la ubicación de la persona.", example = "Calle 50 #10-15, Bogotá, Colombia", required = true)
    private String ubicacion;

    // --- Getters y setters (se mantienen abreviados por brevedad) ---
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
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
}