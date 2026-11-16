package com.pistolerosgalacticos.delivery_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pistolerosgalacticos.delivery_api.domain.MovimientoDeuda;

public interface MovimientoDeudaRepository extends JpaRepository<MovimientoDeuda, Long>{
  
}
