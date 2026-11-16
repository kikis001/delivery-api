package com.pistolerosgalacticos.delivery_api.auth.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SolicitudRegistro(
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    String nombre,

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Size(max = 100, message = "El apellido paterno no puede exceder 100 caracteres")
    String apellidoPaterno,

    @Size(max = 100, message = "El apellido materno no puede exceder 100 caracteres")
    String apellidoMaterno,

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    @Size(max = 150, message = "El email no puede exceder 150 caracteres")
    String email,

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 72, message = "La contraseña debe contener entre 8 y 72 caracteres")
    String contrasena,

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 20, message = "El teléfono no puede exceder 20 caracteres")
    String telefono,

    @NotNull(message = "El rol es obligatorio")
    RolRegistro rol,

    @Valid SolicitudTiendaRegistro tienda) {
}
