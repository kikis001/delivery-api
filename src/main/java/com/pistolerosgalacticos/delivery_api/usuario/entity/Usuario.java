package com.pistolerosgalacticos.delivery_api.usuario.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 20)
    private String telefono;

    @Column(nullable = false, length = 50)
    private String rol;

    @Column(name = "deuda_acumulada", precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal deudaAcumulada = BigDecimal.ZERO;
}
