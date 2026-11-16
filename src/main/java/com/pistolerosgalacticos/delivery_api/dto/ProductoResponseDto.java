package com.pistolerosgalacticos.delivery_api.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductoResponseDto {
  private Long id;
  private String nombre;
  private String descripcion;
  private BigDecimal precioActual;
  private String urlImagen;
}
