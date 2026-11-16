package com.pistolerosgalacticos.delivery_api.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class TiendaResponseDto {
  private Long id;
  private String nombre;
  private String telefonoContacto;
  private String direccionTexto;
  private BigDecimal latitud;
  private BigDecimal longitud;
}
