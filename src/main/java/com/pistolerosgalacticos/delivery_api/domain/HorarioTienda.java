package com.pistolerosgalacticos.delivery_api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "horarios_tienda")
public class HorarioTienda {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "tienda_id", nullable = false)
  private Tienda tienda;

  @Column(name = "dia_semana", nullable = false)
  private Integer diaSemana;

  @Column(name = "hora_apertura", nullable = false)
  private LocalTime horaApertura;

  @Column(name = "hora_cierre", nullable = false)
  private LocalTime horaCierre;
}
