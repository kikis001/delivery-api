package com.pistolerosgalacticos.delivery_api.service;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pistolerosgalacticos.delivery_api.domain.Tienda;
import com.pistolerosgalacticos.delivery_api.dto.TiendaResponseDto;
import com.pistolerosgalacticos.delivery_api.mapper.TiendaMapper;
import com.pistolerosgalacticos.delivery_api.repository.TiendaRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor 
@Transactional(readOnly = true)
public class TiendaService implements ITiendaService {

  private static final ZoneId ZONA_HORARIA = ZoneId.of("America/Mexico_City");
  private final TiendaRepository tiendaRepository;
  private final TiendaMapper tiendaMapper;

  @Override
  public List<TiendaResponseDto> findTiendasAbiertas() {
    ZonedDateTime ahora = ZonedDateTime.now(ZONA_HORARIA);

    Integer diaSemana = ahora.getDayOfWeek().getValue();
    LocalTime horaActual = ahora.toLocalTime();
    List<Tienda> tiendas = tiendaRepository.tiendasAbiertas(diaSemana, horaActual);

    return tiendaMapper.toResponseDtoList(tiendas);
  }
}
