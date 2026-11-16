package com.pistolerosgalacticos.delivery_api.security;

import java.io.Serial;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pistolerosgalacticos.delivery_api.domain.Usuario;
import com.pistolerosgalacticos.delivery_api.enums.RolUsuario;

public final class UsuarioPrincipal implements UserDetails {

  @Serial
  private static final long serialVersionUID = 1L;

  private final Long id;
  private final String email;
  private final String password;
  private final RolUsuario rol;

  private UsuarioPrincipal(Long id, String email, String password, RolUsuario rol) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.rol = rol;
  }

  public static UsuarioPrincipal desde(Usuario usuario) {
    return new UsuarioPrincipal(
        usuario.getId(),
        usuario.getEmail(),
        usuario.getPassword(),
        usuario.getRol());
  }

  public Long getId() {
    return id;
  }

  public RolUsuario getRol() {
    return rol;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
