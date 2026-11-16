package com.pistolerosgalacticos.delivery_api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.pistolerosgalacticos.delivery_api.domain.Producto;
import com.pistolerosgalacticos.delivery_api.dto.ProductoResponseDto;

@Mapper (componentModel = "spring")
public interface ProductoMapper {
  List<ProductoResponseDto> toResponseDtoListById(List<Producto> productos);
}
