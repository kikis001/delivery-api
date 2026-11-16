package com.pistolerosgalacticos.delivery_api.auth.dto;

import java.time.Instant;

public record RespuestaError(String mensaje, Instant fecha) {

  public RespuestaError(String mensaje) {
    this(mensaje, Instant.now());
  }
}
