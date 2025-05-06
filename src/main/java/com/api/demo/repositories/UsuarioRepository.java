package com.api.demo.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.demo.entities.Usuario;
import com.api.demo.entities.UsuarioId;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UsuarioId> {
    Optional<Usuario> findByPersona_Id(Long personaId);

    @Query("SELECT u FROM Usuario u WHERE u.id.login = :login")
    Usuario findByUsername(@Param("login") String login);

    @Query("SELECT u FROM Usuario u WHERE u.id.login = :login AND u.apikey = :apikey")
    Usuario findByUsernameANDAPIKey(@Param("login") String login, @Param("apikey") String APIKey);

}
