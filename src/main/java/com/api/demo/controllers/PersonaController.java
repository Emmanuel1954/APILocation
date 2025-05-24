package com.api.demo.controllers;

import com.api.demo.dtos.PersonaDTO;
import com.api.demo.entities.Persona;
import com.api.demo.services.PersonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personas")
@Tag(name = "Personas", description = "Gestión de personas en el sistema, incluyendo sus datos personales y ubicación.")
@SecurityRequirement(name = "bearerAuth") // Aplica seguridad JWT a todos los endpoints de este controlador
public class PersonaController {
    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @Operation(summary = "Obtener todas las personas", description = "Retorna una lista de todas las personas registradas en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de personas obtenida exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Persona.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Persona>> getAllPersonas() {
        return ResponseEntity.ok(personaService.getAllPersonas());
    }

    @Operation(summary = "Obtener persona por ID", description = "Retorna una persona específica basada en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Persona encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Persona.class))),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada", content = @Content),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(
            @Parameter(description = "ID de la persona a buscar.", example = "1")
            @PathVariable Long id) {
        Optional<Persona> persona = personaService.getPersonaById(id);
        return persona.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nueva persona", description = "Crea una nueva persona en el sistema. Se genera un usuario y API Key asociados automáticamente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Persona creada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Persona.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Persona> createPersona(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto PersonaDTO con los detalles de la nueva persona.", required = true,
                    content = @Content(schema = @Schema(implementation = PersonaDTO.class)))
            @RequestBody PersonaDTO personaDTO) {
        Persona nuevaPersona = personaService.createPersona(personaDTO);
        return new ResponseEntity<>(nuevaPersona, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar persona existente", description = "Actualiza los detalles de una persona existente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Persona actualizada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Persona.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada", content = @Content),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(
            @Parameter(description = "ID de la persona a actualizar.", example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto PersonaDTO con los datos actualizados de la persona.", required = true,
                    content = @Content(schema = @Schema(implementation = PersonaDTO.class)))
            @RequestBody PersonaDTO personaDTO) {
        Persona personaActualizada = personaService.updatePersona(id, personaDTO);
        return personaActualizada != null ? ResponseEntity.ok(personaActualizada) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar persona por ID", description = "Elimina una persona del sistema y sus datos asociados (usuario, coordenadas).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Persona eliminada exitosamente", content = @Content),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada", content = @Content),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(
            @Parameter(description = "ID de la persona a eliminar.", example = "1")
            @PathVariable Long id) {
        personaService.deletePersona(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener persona por número de identificación", description = "Retorna una persona específica basada en su número de identificación.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Persona encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Persona.class))),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada", content = @Content),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
    })
    @GetMapping("/identificacion/{numeroIdentificacion}")
    public ResponseEntity<Persona> getPersonaByNumeroIdentificacion(
            @Parameter(description = "Número de identificación de la persona a buscar.", example = "123456789")
            @PathVariable String numeroIdentificacion) {
        Optional<Persona> persona = personaService.getPersonaByNumeroIdentificacion(numeroIdentificacion);
        return persona.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener personas por edad", description = "Retorna una lista de personas que tienen la edad especificada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de personas por edad obtenida exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Persona.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
    })
    @GetMapping("/edad/{edad}")
    public ResponseEntity<List<Persona>> getPersonasByEdad(
            @Parameter(description = "Edad a buscar.", example = "30")
            @PathVariable Integer edad) {
        return ResponseEntity.ok(personaService.getPersonasByEdad(edad));
    }

    @Operation(summary = "Obtener personas por primer apellido", description = "Retorna una lista de personas que tienen el primer apellido especificado (búsqueda insensible a mayúsculas/minúsculas).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de personas por apellido obtenida exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Persona.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
    })
    @GetMapping("/apellido/{primerApellido}")
    public ResponseEntity<List<Persona>> getPersonasByPrimerApellido(
            @Parameter(description = "Primer apellido a buscar.", example = "Pérez")
            @PathVariable String primerApellido) {
        return ResponseEntity.ok(personaService.getPersonasByPrimerApellido(primerApellido));
    }

    @Operation(summary = "Obtener personas por primer nombre", description = "Retorna una lista de personas que tienen el primer nombre especificado (búsqueda insensible a mayúsculas/minúsculas).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de personas por nombre obtenida exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Persona.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
    })
    @GetMapping("/nombre/{primerNombre}")
    public ResponseEntity<List<Persona>> getPersonasByPrimerNombre(
            @Parameter(description = "Primer nombre a buscar.", example = "Juan")
            @PathVariable String primerNombre) {
        return ResponseEntity.ok(personaService.getPersonasByPrimerNombre(primerNombre));
    }
}