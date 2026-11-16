package com.pistolerosgalacticos.delivery_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pistolerosgalacticos.delivery_api.domain.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long>{
  
}
