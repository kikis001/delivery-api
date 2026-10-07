package com.pistolerosgalacticos.delivery_api.autenticacion;

public record RespuestaRegistro(
        Long id,
        String nombreCompleto,
        String email,
        String rol,
        String mensaje) {
}
