package com.pistolerosgalacticos.delivery_api.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pistolerosgalacticos.delivery_api.domain.Tienda;

public interface TiendaRepository extends JpaRepository<Tienda, Long>{
  
  @Query("""
      SELECT DISTINCT t
      FROM Tienda t
      JOIN t.horarios h
      WHERE t.activa = true
        AND h.diaSemana = :diaSemana
        AND :horaActual >= h.horaApertura
        AND :horaActual < h.horaCierre
      """
  )
  List<Tienda> tiendasAbiertas(@Param("diaSemana") Integer diaSemana, @Param("horaActual") LocalTime horaActual);
}
