package com.pistolerosgalacticos.delivery_api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pistolerosgalacticos.delivery_api.domain.Producto;
import com.pistolerosgalacticos.delivery_api.dto.ProductoResponseDto;
import com.pistolerosgalacticos.delivery_api.mapper.ProductoMapper;
import com.pistolerosgalacticos.delivery_api.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional (readOnly = true)
public class ProductoService implements IProductoService {

  private final ProductoRepository productoRepository;
  private final ProductoMapper productoMapper;

  @Override
  public List<ProductoResponseDto> productoPorTienda(Long tiendaId) {
    List<Producto> productos = productoRepository.findAllByTienda_IdAndActivoTrue(tiendaId);
    return productoMapper.toResponseDtoListById(productos);
  }
}
