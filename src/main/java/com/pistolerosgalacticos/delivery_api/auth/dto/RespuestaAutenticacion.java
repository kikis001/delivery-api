package com.pistolerosgalacticos.delivery_api.auth.dto;

public record RespuestaAutenticacion(String token, String tipo) {

  public RespuestaAutenticacion(String token) {
    this(token, "Bearer");
  }
}
