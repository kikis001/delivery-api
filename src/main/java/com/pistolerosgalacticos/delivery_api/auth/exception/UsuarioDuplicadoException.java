package com.pistolerosgalacticos.delivery_api.auth.exception;

public class UsuarioDuplicadoException extends RuntimeException {

  public UsuarioDuplicadoException(String mensaje) {
    super(mensaje);
  }
}
