package com.api.demo.services;

import com.api.demo.dtos.UsuarioDTO;
import com.api.demo.dtos.UsuarioInfoDTO;
import com.api.demo.entities.Usuario;
import com.api.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public boolean changePassword(Long personaId, UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByPersona_Id(personaId);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setPassword(usuarioDTO.getPassword());
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }

    public Optional<UsuarioInfoDTO> getUsuarioInfo(Long personaId) {
        return usuarioRepository.findByPersona_Id(personaId)
                .map(usuario -> new UsuarioInfoDTO(usuario.getId().getLogin(), usuario.getPassword(), usuario.getApikey()));
    }
}