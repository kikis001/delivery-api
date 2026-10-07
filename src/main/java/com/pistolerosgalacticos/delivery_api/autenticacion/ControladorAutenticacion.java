package com.pistolerosgalacticos.delivery_api.autenticacion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/autenticacion")
@RequiredArgsConstructor
public class ControladorAutenticacion {

    private final ServicioAutenticacion servicioAutenticacion;

    @PostMapping("/registro")
    public ResponseEntity<RespuestaRegistro> registrar(
            @Valid @RequestBody SolicitudRegistro solicitud) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(servicioAutenticacion.registrar(solicitud));
    }

    @PostMapping("/acceso")
    public ResponseEntity<RespuestaAutenticacion> acceder(
            @Valid @RequestBody SolicitudAcceso solicitud) {
        return ResponseEntity.ok(servicioAutenticacion.acceder(solicitud));
    }

    @ExceptionHandler(ExcepcionUsuarioYaExiste.class)
    public ResponseEntity<RespuestaError> manejarUsuarioYaExistente(
            ExcepcionUsuarioYaExiste excepcion) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new RespuestaError(excepcion.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<RespuestaError> manejarErrorAutenticacion() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new RespuestaError("Credenciales incorrectas"));
    }
}
