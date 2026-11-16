package com.pistolerosgalacticos.delivery_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pistolerosgalacticos.delivery_api.domain.HorarioTienda;

public interface HorarioTiendaRepository extends JpaRepository<HorarioTienda, Long>{
  
}
