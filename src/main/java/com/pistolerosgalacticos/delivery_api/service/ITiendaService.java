package com.pistolerosgalacticos.delivery_api.service;

import java.util.List;

import com.pistolerosgalacticos.delivery_api.dto.TiendaResponseDto;

public interface ITiendaService {
  List<TiendaResponseDto> findTiendasAbiertas();

  // List<Tienda>
}