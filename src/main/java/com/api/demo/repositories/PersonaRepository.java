package com.api.demo.repositories;

import com.api.demo.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Optional<Persona> findByNumeroIdentificacion(String numeroIdentificacion);

    @Override
    List<Persona> findAll();

    List<Persona> findByEdad(Integer edad);
    List<Persona> findByPrimerApellidoIgnoreCase(String primerApellido);
    List<Persona> findByPrimerNombreIgnoreCase(String primerNombre);
}