package com.api.demo.controllers;

import com.api.demo.dtos.UsuarioDTO;
import com.api.demo.dtos.UsuarioInfoDTO;
import com.api.demo.services.UsuarioService;
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

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Operaciones de gestión de usuarios y sus credenciales.")
@SecurityRequirement(name = "bearerAuth") // Aplica seguridad JWT a todos los endpoints de este controlador
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Cambiar la contraseña de un usuario", description = "Permite cambiar la contraseña de un usuario asociado a una persona específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Contraseña cambiada exitosamente", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario/Persona no encontrada", content = @Content),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
    })
    @PutMapping("/{personaId}/password")
    public ResponseEntity<Void> changePassword(
            @Parameter(description = "ID de la persona cuyo usuario se va a actualizar.", example = "1")
            @PathVariable Long personaId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto UsuarioDTO con la nueva contraseña.", required = true,
                    content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
            @RequestBody UsuarioDTO usuarioDTO) {
        if (usuarioService.changePassword(personaId, usuarioDTO)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Obtener información de un usuario", description = "Retorna los detalles de login, contraseña (¡cuidado con esto!) y API Key de un usuario asociado a una persona.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Información de usuario obtenida exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioInfoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuario/Persona no encontrada", content = @Content),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
    })
    @GetMapping("/{personaId}/info")
    public ResponseEntity<UsuarioInfoDTO> getUsuarioInfo(
            @Parameter(description = "ID de la persona cuyo usuario se va a consultar.", example = "1")
            @PathVariable Long personaId) {
        Optional<UsuarioInfoDTO> usuarioInfo = usuarioService.getUsuarioInfo(personaId);
        return usuarioInfo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}