package com.pistolerosgalacticos.delivery_api.auth.dto;

public record RespuestaRegistro(
    Long id,
    String nombre,
    String apellidoPaterno,
    String apellidoMaterno,
    String email,
    RolRegistro rol,
    String mensaje) {
}
