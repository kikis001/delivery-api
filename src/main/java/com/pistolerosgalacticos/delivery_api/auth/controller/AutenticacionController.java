package com.pistolerosgalacticos.delivery_api.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pistolerosgalacticos.delivery_api.auth.dto.RespuestaAutenticacion;
import com.pistolerosgalacticos.delivery_api.auth.dto.RespuestaRegistro;
import com.pistolerosgalacticos.delivery_api.auth.dto.SolicitudAcceso;
import com.pistolerosgalacticos.delivery_api.auth.dto.SolicitudRegistro;
import com.pistolerosgalacticos.delivery_api.auth.service.AutenticacionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/autenticacion")
@RequiredArgsConstructor
public class AutenticacionController {

  private final AutenticacionService autenticacionService;

  @PostMapping("/acceso")
  public ResponseEntity<RespuestaAutenticacion> acceder(
      @Valid @RequestBody SolicitudAcceso solicitud) {
    return ResponseEntity.ok(autenticacionService.acceder(solicitud));
  }

  @PostMapping("/registro")
  public ResponseEntity<RespuestaRegistro> registrar(
      @Valid @RequestBody SolicitudRegistro solicitud) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(autenticacionService.registrar(solicitud));
  }
}
