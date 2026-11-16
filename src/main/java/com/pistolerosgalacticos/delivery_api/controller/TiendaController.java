package com.pistolerosgalacticos.delivery_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pistolerosgalacticos.delivery_api.dto.TiendaResponseDto;
import com.pistolerosgalacticos.delivery_api.service.ITiendaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/tiendas")
@RequiredArgsConstructor
public class TiendaController {
  
  private final ITiendaService tiendaService;
  
  @GetMapping
  public ResponseEntity<List<TiendaResponseDto>> tiendasAbiertas() {
    return ResponseEntity.ok(tiendaService.findTiendasAbiertas());
  }
}
