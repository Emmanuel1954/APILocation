package com.api.demo.controllers;
import com.api.demo.entities.Coordenadas;
import com.api.demo.services.CoordenadasServiceImpl; // O CoordenadasService si lo anotas como @Service
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ApiLocation") // O considera cambiarlo a algo como /api/coordenadas para consistencia
@Tag(name = "Coordenadas", description = "Operaciones para consultar las coordenadas de ubicación de las personas.")
@SecurityRequirement(name = "bearerAuth") // Aplica seguridad JWT
public class CoordenadasController {

	@Autowired
	@Qualifier("CoordenadasService") // Asegúrate que tu servicio CoordenadasServiceImpl está anotado con @Service("CoordenadasService")
	private CoordenadasServiceImpl coordenadaService;

	@Operation(summary = "Consultar todas las coordenadas paginadas", description = "Retorna una lista paginada de todas las coordenadas registradas.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de coordenadas obtenida exitosamente",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = Coordenadas.class))),
			@ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
	})
	@GetMapping("/coordenadas")
	public List<Coordenadas> consultarAllCoordenadas(
			@Parameter(hidden = true) // Oculta el parámetro Pageable si quieres que SpringDoc lo infiera automáticamente
			Pageable pageable) {
		return coordenadaService.consultarAllCoordenadas(pageable);
	}

	@Operation(summary = "Consultar una coordenada por ID", description = "Retorna una coordenada específica basada en su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Coordenada encontrada",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = Coordenadas.class))),
			@ApiResponse(responseCode = "404", description = "Coordenada no encontrada", content = @Content),
			@ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
	})
	@GetMapping("/{id}")
	public Coordenadas consultarCoordenada(
			@Parameter(description = "ID de la coordenada a buscar.", example = "1")
			@PathVariable int id) {
		return coordenadaService.consultarcordenada(id);
	}
}