package com.pistolerosgalacticos.delivery_api.auth.dto;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SolicitudTiendaRegistro(
    @NotBlank(message = "El nombre de la tienda es obligatorio")
    @Size(max = 150, message = "El nombre de la tienda no puede exceder 150 caracteres")
    String nombre,

    @NotBlank(message = "El teléfono de la tienda es obligatorio")
    @Size(max = 20, message = "El teléfono de la tienda no puede exceder 20 caracteres")
    String telefonoContacto,

    @NotBlank(message = "La dirección de la tienda es obligatoria")
    @Size(max = 255, message = "La dirección de la tienda no puede exceder 255 caracteres")
    String direccionTexto,

    @NotNull(message = "La latitud es obligatoria")
    @DecimalMin(value = "-90.00000000", message = "La latitud debe ser mayor o igual a -90")
    @DecimalMax(value = "90.00000000", message = "La latitud debe ser menor o igual a 90")
    @Digits(integer = 2, fraction = 8, message = "La latitud admite hasta 8 decimales")
    BigDecimal latitud,

    @NotNull(message = "La longitud es obligatoria")
    @DecimalMin(value = "-180.00000000", message = "La longitud debe ser mayor o igual a -180")
    @DecimalMax(value = "180.00000000", message = "La longitud debe ser menor o igual a 180")
    @Digits(integer = 3, fraction = 8, message = "La longitud admite hasta 8 decimales")
    BigDecimal longitud,

    @NotNull(message = "Los horarios son obligatorios")
    List<@Valid SolicitudHorarioRegistro> horarios) {
}
