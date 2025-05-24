package com.api.demo.entities;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "COOR") // Nombre para JPQL/HQL
@Table(name = "Coordenadas") // Nombre de la tabla en la BD
@Schema(description = "Registra las coordenadas geográficas (latitud, longitud) de una persona en un momento dado.")
public class Coordenadas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_coordenada")
	@Schema(description = "Identificador único de la coordenada.", example = "10")
	public int id;

	// Nota: Aquí 'persona' es un long, si debería ser una relación @OneToOne con la entidad Persona, deberías cambiarlo.
	// Para Swagger, se documentará como un número.
	@Column(name = "persona")
	@Schema(description = "ID de la persona asociada a estas coordenadas.", example = "1", required = true)
	public long persona;

	@Column(name = "marca")
	@Schema(description = "Marca o nombre asociado a la coordenada (ej. nombre de la persona).", example = "Juan Pérez")
	public String marca;

	@Column(name = "longitud")
	@Schema(description = "La longitud geográfica actual.", example = "-74.0817", required = true)
	public Double longitud;

	@Column(name = "latitud")
	@Schema(description = "La latitud geográfica actual.", example = "4.7110", required = true)
	public Double latitud;

	@Column(name = "longitud_anterior")
	@Schema(description = "La longitud geográfica anterior (para historial).", example = "-74.0820")
	public Double longitud_anterior;

	@Column(name = "latitud_anterior")
	@Schema(description = "La latitud geográfica anterior (para historial).", example = "4.7100")
	public Double latitud_anterior;

	public Coordenadas() {}

	public Coordenadas(long persona, String marca, double longitud, double latitud) {
		this.persona = persona;
		this.marca = marca;
		this.longitud = longitud;
		this.latitud = latitud;
	}

	public Coordenadas(int id, long persona, String marca, double longitud, double latitud) {
		this.id = id;
		this.persona = persona;
		this.marca = marca;
		this.longitud = longitud;
		this.latitud = latitud;
	}

	public Coordenadas(long persona, String marca, double longitud, double latitud, double latitud_anterior, double longitud_anterior) {
		this.persona = persona;
		this.marca = marca;
		this.longitud = longitud;
		this.latitud = latitud;
		this.latitud_anterior = latitud_anterior;
		this.longitud_anterior = longitud_anterior;
	}

	// --- Getters y Setters ---
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public long getPersona() { return persona; }
	public void setPersona(long persona) { this.persona = persona; }
	public String getMarca() { return marca; }
	public void setMarca(String marca) { this.marca = marca; }
	public Double getLongitud() { return longitud; }
	public void setLongitud(Double longitud) { this.longitud = longitud; }
	public Double getLatitud() { return latitud; }
	public void setLatitud(Double latitud) { this.latitud = latitud; }
	public Double getLongitud_anterior() { return longitud_anterior; }
	public void setLongitud_anterior(Double longitud_anterior) { this.longitud_anterior = longitud_anterior; }
	public Double getLatitud_anterior() { return latitud_anterior; }
	public void setlatitud_anterior(Double latitud_anterior) { this.latitud_anterior = latitud_anterior; }
}