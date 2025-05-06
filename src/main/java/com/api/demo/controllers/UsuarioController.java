package com.api.demo.controllers;


import com.api.demo.dtos.UsuarioDTO;
import com.api.demo.dtos.UsuarioInfoDTO;
import com.api.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PutMapping("/{personaId}/password")
    public ResponseEntity<Void> changePassword(@PathVariable Long personaId, @RequestBody UsuarioDTO usuarioDTO) {
        if (usuarioService.changePassword(personaId, usuarioDTO)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{personaId}/info")
    public ResponseEntity<UsuarioInfoDTO> getUsuarioInfo(@PathVariable Long personaId) {
        Optional<UsuarioInfoDTO> usuarioInfo = usuarioService.getUsuarioInfo(personaId);
        return usuarioInfo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}