package com.pistolerosgalacticos.delivery_api.security;

import java.util.Locale;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pistolerosgalacticos.delivery_api.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String email) {
    String emailNormalizado = email.trim().toLowerCase(Locale.ROOT);

    return usuarioRepository.findByEmailIgnoreCase(emailNormalizado)
        .map(UsuarioPrincipal::desde)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
  }
}
