package com.pistolerosgalacticos.delivery_api.security;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pistolerosgalacticos.delivery_api.autenticacion.RespuestaAutenticacion;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/prueba")
@RequiredArgsConstructor
public class ControladorPruebaJwt {

    private final JwtService jwtService;

    @GetMapping("/token")
    public RespuestaAutenticacion obtenerToken() {
        return new RespuestaAutenticacion(jwtService.generateToken("prueba-jwt"));
    }

    @GetMapping("/privado")
    public Map<String, String> endpointPrivado(Authentication autenticacion) {
        return Map.of(
                "mensaje", "JWT válido: acceso permitido",
                "sujeto", autenticacion.getName());
    }
}
