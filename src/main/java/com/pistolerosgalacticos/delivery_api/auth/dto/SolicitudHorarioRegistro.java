package com.pistolerosgalacticos.delivery_api.auth.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SolicitudHorarioRegistro(
    @NotNull(message = "El día de la semana es obligatorio")
    @Min(value = 1, message = "El día de la semana debe estar entre 1 y 7")
    @Max(value = 7, message = "El día de la semana debe estar entre 1 y 7")
    Integer diaSemana,

    @NotNull(message = "La hora de apertura es obligatoria")
    LocalTime horaApertura,

    @NotNull(message = "La hora de cierre es obligatoria")
    LocalTime horaCierre) {
}
