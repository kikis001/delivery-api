package com.pistolerosgalacticos.delivery_api.service;

import java.util.List;

import com.pistolerosgalacticos.delivery_api.dto.ProductoResponseDto;

public interface IProductoService {
  List<ProductoResponseDto> productoPorTienda(Long tiendaId);  
}
