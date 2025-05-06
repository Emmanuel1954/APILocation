package com.api.demo.controllers;

import com.api.demo.dtos.PersonaDTO;
import com.api.demo.entities.Persona;
import com.api.demo.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }
@GetMapping
public ResponseEntity<List<Persona>> getAllPersonas() {
    return ResponseEntity.ok(personaService.getAllPersonas());
}

@GetMapping("/{id}")
public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
    Optional<Persona> persona = personaService.getPersonaById(id);
    return persona.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
}

@PostMapping
public ResponseEntity<Persona> createPersona(@RequestBody PersonaDTO personaDTO) {
    Persona nuevaPersona = personaService.createPersona(personaDTO);
    return new ResponseEntity<>(nuevaPersona, HttpStatus.CREATED);
}

@PutMapping("/{id}")
public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody PersonaDTO personaDTO) {
    Persona personaActualizada = personaService.updatePersona(id, personaDTO);
    return personaActualizada != null ? ResponseEntity.ok(personaActualizada) : ResponseEntity.notFound().build();
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
    personaService.deletePersona(id);
    return ResponseEntity.noContent().build();
}

@GetMapping("/identificacion/{numeroIdentificacion}")
public ResponseEntity<Persona> getPersonaByNumeroIdentificacion(@PathVariable String numeroIdentificacion) {
    Optional<Persona> persona = personaService.getPersonaByNumeroIdentificacion(numeroIdentificacion);
    return persona.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
}

@GetMapping("/edad/{edad}")
public ResponseEntity<List<Persona>> getPersonasByEdad(@PathVariable Integer edad) {
    return ResponseEntity.ok(personaService.getPersonasByEdad(edad));
}

@GetMapping("/apellido/{primerApellido}")
public ResponseEntity<List<Persona>> getPersonasByPrimerApellido(@PathVariable String primerApellido) {
    return ResponseEntity.ok(personaService.getPersonasByPrimerApellido(primerApellido));
}

@GetMapping("/nombre/{primerNombre}")
public ResponseEntity<List<Persona>> getPersonasByPrimerNombre(@PathVariable String primerNombre) {
    return ResponseEntity.ok(personaService.getPersonasByPrimerNombre(primerNombre));
}
}