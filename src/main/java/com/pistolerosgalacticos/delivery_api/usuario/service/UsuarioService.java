package com.pistolerosgalacticos.delivery_api.usuario.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.pistolerosgalacticos.delivery_api.usuario.entity.Usuario;

public interface UsuarioService extends UserDetailsService {

    Usuario registrarUsuario(Usuario usuario);

    Optional<Usuario> buscarPorEmail(String email);

    boolean existePorEmail(String email);
}
