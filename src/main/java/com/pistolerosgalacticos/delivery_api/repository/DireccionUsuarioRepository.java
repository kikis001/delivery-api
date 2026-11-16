package com.pistolerosgalacticos.delivery_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pistolerosgalacticos.delivery_api.domain.DireccionUsuario;

public interface DireccionUsuarioRepository extends JpaRepository<DireccionUsuario, Long>{
  
}
