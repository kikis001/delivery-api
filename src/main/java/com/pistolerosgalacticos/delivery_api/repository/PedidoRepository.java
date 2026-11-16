package com.pistolerosgalacticos.delivery_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pistolerosgalacticos.delivery_api.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
  
}
