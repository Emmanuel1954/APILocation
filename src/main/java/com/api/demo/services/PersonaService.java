package com.api.demo.services;
import com.api.demo.dtos.PersonaDTO;
import com.api.demo.entities.Persona;
import com.api.demo.entities.Usuario;
import com.api.demo.entities.UsuarioId;
import com.api.demo.repositories.PersonaRepository;
import com.api.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonaService {
    private final PersonaRepository personaRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public PersonaService(PersonaRepository personaRepository, UsuarioRepository usuarioRepository) {
        this.personaRepository = personaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    public Optional<Persona> getPersonaById(Long id) {
        return personaRepository.findById(id);
    }

    public Optional<Persona> getPersonaByNumeroIdentificacion(String numeroIdentificacion) {
        return personaRepository.findByNumeroIdentificacion(numeroIdentificacion);
    }

    public List<Persona> getPersonasByEdad(Integer edad) {
        return personaRepository.findByEdad(edad);
    }

    public List<Persona> getPersonasByPrimerApellido(String primerApellido) {
        return personaRepository.findByPrimerApellidoIgnoreCase(primerApellido);
    }

    public List<Persona> getPersonasByPrimerNombre(String primerNombre) {
        return personaRepository.findByPrimerNombreIgnoreCase(primerNombre);
    }

    @Transactional
    public Persona createPersona(PersonaDTO personaDTO) {
        Persona persona = new Persona();
        persona.setPrimerNombre(personaDTO.getPrimerNombre());
        persona.setSegundoNombre(personaDTO.getSegundoNombre());
        persona.setPrimerApellido(personaDTO.getPrimerApellido());
        persona.setSegundoApellido(personaDTO.getSegundoApellido());
        persona.setNumeroIdentificacion(personaDTO.getNumeroIdentificacion());
        persona.setFechaNacimiento(personaDTO.getFechaNacimiento());
        persona.calcularEdad();

        Persona savedPersona = personaRepository.save(persona);
        createUserForPersona(savedPersona);
        return savedPersona;
    }

    @Transactional
    public Persona updatePersona(Long id, PersonaDTO personaDTO) {
        return personaRepository.findById(id)
                .map(persona -> {
                    persona.setPrimerNombre(personaDTO.getPrimerNombre());
                    persona.setSegundoNombre(personaDTO.getSegundoNombre());
                    persona.setPrimerApellido(personaDTO.getPrimerApellido());
                    persona.setSegundoApellido(personaDTO.getSegundoApellido());
                    persona.setNumeroIdentificacion(personaDTO.getNumeroIdentificacion());
                    persona.setFechaNacimiento(personaDTO.getFechaNacimiento());
                    persona.calcularEdad();
                    return personaRepository.save(persona);
                })
                .orElse(null);
    }

    public void deletePersona(Long id) {
        personaRepository.deleteById(id);
    }

    private void createUserForPersona(Persona persona) {
        Usuario usuario = new Usuario();
        String login = generateLogin(persona);
        String password = generatePassword();
        String apikey = generateApiKey();

        UsuarioId usuarioId = new UsuarioId(persona.getId(), login);
        usuario.setId(usuarioId);
        usuario.setPersona(persona);
        usuario.setPassword(password);
        usuario.setApikey(apikey);

        usuarioRepository.save(usuario);
    }

    private String generateLogin(Persona persona) {
        String primerNombreCompleto = (persona.getPrimerNombre() + (persona.getSegundoNombre() != null ? persona.getSegundoNombre() : "")).replaceAll("\\s+", "");
        return primerNombreCompleto + persona.getPrimerApellido().substring(0, 1).toUpperCase() + persona.getId();
    }

    private String generatePassword() {
        return UUID.randomUUID().toString().substring(0, 10); // Genera una contraseña aleatoria
    }

    private String generateApiKey() {
        return UUID.randomUUID().toString();
    }
}