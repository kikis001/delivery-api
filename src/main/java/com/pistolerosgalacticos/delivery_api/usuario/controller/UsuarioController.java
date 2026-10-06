package com.pistolerosgalacticos.delivery_api.usuario.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pistolerosgalacticos.delivery_api.usuario.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
  private final UsuarioService usuarioService;

  @GetMapping("/saludo")
  public ResponseEntity<String> saludar() {
    return ResponseEntity.ok("funciona");
  }
}
