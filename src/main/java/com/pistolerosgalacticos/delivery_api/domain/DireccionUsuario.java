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
import java.math.BigDecimal;
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
@Table(name = "direcciones_usuario")
public class DireccionUsuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "usuario_id", nullable = false)
  private Usuario usuario;

  @Column(nullable = false, length = 50)
  private String alias;

  @Column(name = "direccion_texto", nullable = false, length = 255)
  private String direccionTexto;

  @Column(nullable = false, precision = 10, scale = 8)
  private BigDecimal latitud;

  @Column(nullable = false, precision = 11, scale = 8)
  private BigDecimal longitud;

  @Builder.Default
  @Column(name = "es_principal", nullable = false)
  private Boolean esPrincipal = false;
}
