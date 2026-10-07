package com.pistolerosgalacticos.delivery_api.autenticacion;

public class ExcepcionUsuarioYaExiste extends RuntimeException {

    public ExcepcionUsuarioYaExiste(String mensaje) {
        super(mensaje);
    }
}
