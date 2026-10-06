package com.pistolerosgalacticos.delivery_api.usuario.service;

import org.springframework.stereotype.Service;

import com.pistolerosgalacticos.delivery_api.usuario.entity.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

  @Override
  public Usuario registrarUsuario(Usuario usuario) {
    
    return usuario;
  }
}
