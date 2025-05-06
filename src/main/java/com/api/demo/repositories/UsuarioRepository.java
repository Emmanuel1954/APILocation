package com.api.demo.repositories;


import com.api.demo.entities.Usuario;
import com.api.demo.entities.UsuarioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UsuarioId> {
    Optional<Usuario> findByPersona_Id(Long personaId);
}