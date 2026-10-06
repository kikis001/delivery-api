package com.pistolerosgalacticos.delivery_api.pedido.entity;

import com.pistolerosgalacticos.delivery_api.usuario.entity.Usuario;
import com.pistolerosgalacticos.delivery_api.tienda.entity.Tienda;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tienda_id", nullable = false)
    private Tienda tienda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repartidor_id")
    private Usuario repartidor;

    @Column(nullable = false, length = 50)
    private String estatus;

    @Column(name = "metodo_pago", nullable = false, length = 50)
    private String metodoPago;

    @Column(name = "pin_entrega", length = 10)
    private String pinEntrega;

    @Column(name = "url_foto_evidencia", columnDefinition = "TEXT")
    private String urlFotoEvidencia;

    @Column(name = "notas_entrega", columnDefinition = "TEXT")
    private String notasEntrega;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;
    
    @PrePersist
    protected void onCreate() {
        if (fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now();
        }
    }
}
