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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@Table(name = "pedidos")
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "cliente_id", nullable = false)
  private Usuario cliente;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "tienda_id", nullable = false)
  private Tienda tienda;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "repartidor_id")
  private Usuario repartidor;

  @Column(nullable = false, length = 30)
  private String estatus;

  @Column(name = "metodo_pago", nullable = false, length = 30)
  private String metodoPago;

  @Column(name = "direccion_entrega", nullable = false, length = 255)
  private String direccionEntrega;

  @Column(name = "latitud_entrega", nullable = false, precision = 10, scale = 8)
  private BigDecimal latitudEntrega;

  @Column(name = "longitud_entrega", nullable = false, precision = 11, scale = 8)
  private BigDecimal longitudEntrega;

  @Builder.Default
  @Column(name = "costo_envio", nullable = false, precision = 10, scale = 2)
  private BigDecimal costoEnvio = BigDecimal.ZERO;

  @Builder.Default
  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal propina = BigDecimal.ZERO;

  @Column(name = "pin_entrega", length = 10)
  private String pinEntrega;

  @Column(name = "url_foto_evidencia", length = 500)
  private String urlFotoEvidencia;

  @Column(name = "notas_entrega", length = 500)
  private String notasEntrega;

  @Column(name = "fecha_creacion", nullable = false, updatable = false)
  private LocalDateTime fechaCreacion;

  @Builder.Default
  @OneToMany(mappedBy = "pedido")
  private List<DetallePedido> detalles = new ArrayList<>();

  @Builder.Default
  @OneToMany(mappedBy = "pedido")
  private List<MovimientoDeuda> movimientosDeuda = new ArrayList<>();

  @PrePersist
  void asignarFechaCreacion() {
    if (fechaCreacion == null) {
      fechaCreacion = LocalDateTime.now();
    }
  }
}
