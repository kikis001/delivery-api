package com.pistolerosgalacticos.delivery_api.tienda.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pistolerosgalacticos.delivery_api.tienda.service.TiendaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/tiendas")
@RequiredArgsConstructor
public class TiendaControllet {
  private final TiendaService tiendaService;

  @GetMapping("/saludo")
  public ResponseEntity<String> saludar() {
    return ResponseEntity.ok("funciona");
  }
}
