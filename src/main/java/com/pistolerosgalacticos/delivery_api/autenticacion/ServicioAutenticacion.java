package com.pistolerosgalacticos.delivery_api.autenticacion;

import java.util.Locale;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pistolerosgalacticos.delivery_api.security.JwtService;
import com.pistolerosgalacticos.delivery_api.usuario.entity.Usuario;
import com.pistolerosgalacticos.delivery_api.usuario.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicioAutenticacion {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public RespuestaRegistro registrar(SolicitudRegistro solicitud) {
        String email = normalizarEmail(solicitud.email());
        if (usuarioService.existePorEmail(email)) {
            throw new ExcepcionUsuarioYaExiste("Ya existe un usuario con ese email");
        }

        Usuario usuario = Usuario.builder()
                .nombreCompleto(solicitud.nombreCompleto().trim())
                .email(email)
                .password(passwordEncoder.encode(solicitud.contrasena()))
                .rol("CLIENTE")
                .build();

        Usuario guardado = usuarioService.registrarUsuario(usuario);
        return new RespuestaRegistro(
                guardado.getId(),
                guardado.getNombreCompleto(),
                guardado.getEmail(),
                guardado.getRol(),
                "Usuario registrado correctamente");
    }

    public RespuestaAutenticacion acceder(SolicitudAcceso solicitud) {
        String email = normalizarEmail(solicitud.email());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, solicitud.contrasena()));

        return new RespuestaAutenticacion(jwtService.generateToken(email));
    }

    private String normalizarEmail(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }
}
