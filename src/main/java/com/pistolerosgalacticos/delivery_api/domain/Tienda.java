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
@Table(name = "tiendas")
public class Tienda {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "administrador_id", nullable = false)
  private Usuario administrador;

  @Column(nullable = false, length = 150)
  private String nombre;

  @Column(name = "telefono_contacto", nullable = false, length = 20)
  private String telefonoContacto;

  @Column(name = "direccion_texto", nullable = false, length = 255)
  private String direccionTexto;

  @Column(nullable = false, precision = 10, scale = 8)
  private BigDecimal latitud;

  @Column(nullable = false, precision = 11, scale = 8)
  private BigDecimal longitud;

  @Builder.Default
  @Column(nullable = false)
  private Boolean activa = true;

  @Builder.Default
  @OneToMany(mappedBy = "tienda")
  private List<EmpleadoTienda> empleados = new ArrayList<>();

  @Builder.Default
  @OneToMany(mappedBy = "tienda")
  private List<HorarioTienda> horarios = new ArrayList<>();

  @Builder.Default
  @OneToMany(mappedBy = "tienda")
  private List<Producto> productos = new ArrayList<>();

  @Builder.Default
  @OneToMany(mappedBy = "tienda")
  private List<Pedido> pedidos = new ArrayList<>();
}
