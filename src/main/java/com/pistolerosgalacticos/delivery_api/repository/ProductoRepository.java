package com.pistolerosgalacticos.delivery_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pistolerosgalacticos.delivery_api.domain.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

  List<Producto> findAllByTienda_IdAndActivoTrue(Long tiendaId);
}
