package com.pistolerosgalacticos.delivery_api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
@Table(name = "productos")
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "tienda_id", nullable = false)
  private Tienda tienda;

  @Column(nullable = false, length = 150)
  private String nombre;

  @Column(columnDefinition = "TEXT")
  private String descripcion;

  @Column(name = "precio_actual", nullable = false, precision = 10, scale = 2)
  private BigDecimal precioActual;

  @Column(name = "url_imagen", length = 500)
  private String urlImagen;

  @Builder.Default
  @Column(nullable = false)
  private Boolean activo = true;

  @Builder.Default
  @OneToMany(mappedBy = "producto")
  private List<DetallePedido> detallesPedido = new ArrayList<>();
}
