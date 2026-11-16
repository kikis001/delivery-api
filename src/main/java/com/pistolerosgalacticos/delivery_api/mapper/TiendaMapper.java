package com.pistolerosgalacticos.delivery_api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.pistolerosgalacticos.delivery_api.domain.Tienda;
import com.pistolerosgalacticos.delivery_api.dto.TiendaResponseDto;

@Mapper (componentModel = "spring")
public interface TiendaMapper {
  
  TiendaResponseDto toResponseDto(Tienda tienda); 

  // le pasamos la lista de tiendas que nos regresa la entidad y las convertirmos a un dto
  List<TiendaResponseDto> toResponseDtoList(List<Tienda> tiendas);
}
