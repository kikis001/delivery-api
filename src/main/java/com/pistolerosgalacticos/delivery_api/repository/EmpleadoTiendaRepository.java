package com.pistolerosgalacticos.delivery_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pistolerosgalacticos.delivery_api.domain.EmpleadoTienda;

public interface EmpleadoTiendaRepository extends JpaRepository<EmpleadoTienda, Long> {
  
}
