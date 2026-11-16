package com.pistolerosgalacticos.delivery_api.domain;

import com.pistolerosgalacticos.delivery_api.enums.RolUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(
    name = "usuarios",
    uniqueConstraints = {
      @UniqueConstraint(name = "uk_usuarios_email", columnNames = "email"),
      @UniqueConstraint(name = "uk_usuarios_telefono", columnNames = "telefono")
    })
public class Usuario {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false, length = 150)
  private String email;

  @Column(nullable = false, length = 20)
  private String telefono;
  
  @Column(nullable = false, length = 100)
  private String nombre;

  @Column(name = "apellido_materno", length = 100)
  private String apellidoMaterno;

  @Column(name = "apellido_paterno", nullable = false, length = 100)
  private String apellidoPaterno;

  @Column(nullable = false, length = 255)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(name = "rol", nullable = false, length = 30)
  private RolUsuario rol;

  @Builder.Default
  @OneToMany(mappedBy = "usuario")
  private List<DireccionUsuario> direcciones = new ArrayList<>();

  @Builder.Default
  @OneToMany(mappedBy = "administrador")
  private List<Tienda> tiendasAdministradas = new ArrayList<>();

  @Builder.Default
  @OneToMany(mappedBy = "usuario")
  private List<EmpleadoTienda> empleos = new ArrayList<>();

  @Builder.Default
  @OneToMany(mappedBy = "cliente")
  private List<Pedido> pedidosComoCliente = new ArrayList<>();

  @Builder.Default
  @OneToMany(mappedBy = "repartidor")
  private List<Pedido> pedidosComoRepartidor = new ArrayList<>();

  @Builder.Default
  @OneToMany(mappedBy = "usuario")
  private List<MovimientoDeuda> movimientosDeuda = new ArrayList<>();
}
