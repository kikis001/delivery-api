package com.pistolerosgalacticos.delivery_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pistolerosgalacticos.delivery_api.dto.ProductoResponseDto;
import com.pistolerosgalacticos.delivery_api.service.IProductoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/tiendas/{tiendaId}/productos")
@RequiredArgsConstructor
public class ProductoController {
  private final IProductoService productoService;

  @GetMapping
  public ResponseEntity<List<ProductoResponseDto>> obtenerProductos(@PathVariable Long tiendaId) {
    return ResponseEntity.ok(productoService.productoPorTienda(tiendaId));
  }
}
