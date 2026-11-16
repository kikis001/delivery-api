package com.pistolerosgalacticos.delivery_api.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pistolerosgalacticos.delivery_api.auth.dto.RespuestaError;

@RestControllerAdvice(basePackages = "com.pistolerosgalacticos.delivery_api.auth")
public class AutenticacionExceptionHandler {

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<RespuestaError> manejarCredencialesInvalidas() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(new RespuestaError("Correo o contraseña incorrectos"));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<RespuestaError> manejarValidacion(
      MethodArgumentNotValidException excepcion) {
    String mensaje = excepcion.getBindingResult().getFieldErrors().stream()
        .findFirst()
        .map(FieldError::getDefaultMessage)
        .orElse("La solicitud no es válida");

    return ResponseEntity.badRequest().body(new RespuestaError(mensaje));
  }

  @ExceptionHandler(SolicitudRegistroInvalidaException.class)
  public ResponseEntity<RespuestaError> manejarSolicitudInvalida(
      SolicitudRegistroInvalidaException excepcion) {
    return ResponseEntity.badRequest().body(new RespuestaError(excepcion.getMessage()));
  }

  @ExceptionHandler(UsuarioDuplicadoException.class)
  public ResponseEntity<RespuestaError> manejarDuplicado(
      UsuarioDuplicadoException excepcion) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(new RespuestaError(excepcion.getMessage()));
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<RespuestaError> manejarIntegridad(
      DataIntegrityViolationException excepcion) {
    String detalle = excepcion.getMostSpecificCause().getMessage();
    String mensaje;

    if (detalle != null && detalle.contains("uk_usuarios_email")) {
      mensaje = "Ya existe un usuario con ese correo";
    } else if (detalle != null && detalle.contains("uk_usuarios_telefono")) {
      mensaje = "Ya existe un usuario con ese teléfono";
    } else {
      mensaje = "No fue posible guardar la cuenta; revisa los datos obligatorios";
    }

    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(new RespuestaError(mensaje));
  }
}
