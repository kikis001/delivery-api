package com.pistolerosgalacticos.delivery_api.auth.service;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pistolerosgalacticos.delivery_api.auth.dto.RespuestaAutenticacion;
import com.pistolerosgalacticos.delivery_api.auth.dto.RespuestaRegistro;
import com.pistolerosgalacticos.delivery_api.auth.dto.RolRegistro;
import com.pistolerosgalacticos.delivery_api.auth.dto.SolicitudAcceso;
import com.pistolerosgalacticos.delivery_api.auth.dto.SolicitudHorarioRegistro;
import com.pistolerosgalacticos.delivery_api.auth.dto.SolicitudRegistro;
import com.pistolerosgalacticos.delivery_api.auth.dto.SolicitudTiendaRegistro;
import com.pistolerosgalacticos.delivery_api.auth.exception.SolicitudRegistroInvalidaException;
import com.pistolerosgalacticos.delivery_api.auth.exception.UsuarioDuplicadoException;
import com.pistolerosgalacticos.delivery_api.domain.HorarioTienda;
import com.pistolerosgalacticos.delivery_api.domain.Tienda;
import com.pistolerosgalacticos.delivery_api.domain.Usuario;
import com.pistolerosgalacticos.delivery_api.enums.RolUsuario;
import com.pistolerosgalacticos.delivery_api.repository.HorarioTiendaRepository;
import com.pistolerosgalacticos.delivery_api.repository.TiendaRepository;
import com.pistolerosgalacticos.delivery_api.repository.UsuarioRepository;
import com.pistolerosgalacticos.delivery_api.security.JwtService;
import com.pistolerosgalacticos.delivery_api.security.UsuarioPrincipal;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutenticacionService {

  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final UsuarioRepository usuarioRepository;
  private final TiendaRepository tiendaRepository;
  private final HorarioTiendaRepository horarioTiendaRepository;

  public RespuestaAutenticacion acceder(SolicitudAcceso solicitud) {
    String email = normalizarEmail(solicitud.email());

    Authentication autenticacion = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(email, solicitud.contrasena()));

    UsuarioPrincipal usuario = (UsuarioPrincipal) autenticacion.getPrincipal();
    return new RespuestaAutenticacion(jwtService.generarToken(usuario));
  }

  @Transactional
  public RespuestaRegistro registrar(SolicitudRegistro solicitud) {
    validarDatosPorRol(solicitud);

    String email = normalizarEmail(solicitud.email());
    String telefono = normalizarTelefono(solicitud.telefono(), "del usuario");

    if (usuarioRepository.existsByEmailIgnoreCase(email)) {
      throw new UsuarioDuplicadoException("Ya existe un usuario con ese correo");
    }
    if (usuarioRepository.existsByTelefono(telefono)) {
      throw new UsuarioDuplicadoException("Ya existe un usuario con ese teléfono");
    }

    Usuario usuario = Usuario.builder()
        .nombre(solicitud.nombre().trim())
        .apellidoPaterno(solicitud.apellidoPaterno().trim())
        .apellidoMaterno(normalizarOpcional(solicitud.apellidoMaterno()))
        .email(email)
        .telefono(telefono)
        .password(passwordEncoder.encode(solicitud.contrasena()))
        .rol(RolUsuario.valueOf(solicitud.rol().name()))
        .build();

    Usuario usuarioGuardado = usuarioRepository.save(usuario);

    if (solicitud.rol() == RolRegistro.ADMIN_TIENDA) {
      registrarTienda(usuarioGuardado, solicitud.tienda());
    }

    return new RespuestaRegistro(
        usuarioGuardado.getId(),
        usuarioGuardado.getNombre(),
        usuarioGuardado.getApellidoPaterno(),
        usuarioGuardado.getApellidoMaterno(),
        usuarioGuardado.getEmail(),
        solicitud.rol(),
        "Usuario registrado correctamente");
  }

  private void registrarTienda(Usuario administrador, SolicitudTiendaRegistro solicitud) {
    validarHorarios(solicitud.horarios());

    Tienda tienda = Tienda.builder()
        .administrador(administrador)
        .nombre(solicitud.nombre().trim())
        .telefonoContacto(normalizarTelefono(solicitud.telefonoContacto(), "de la tienda"))
        .direccionTexto(solicitud.direccionTexto().trim())
        .latitud(solicitud.latitud())
        .longitud(solicitud.longitud())
        .activa(true)
        .build();

    Tienda tiendaGuardada = tiendaRepository.save(tienda);

    List<HorarioTienda> horarios = solicitud.horarios().stream()
        .map(horario -> HorarioTienda.builder()
            .tienda(tiendaGuardada)
            .diaSemana(horario.diaSemana())
            .horaApertura(horario.horaApertura())
            .horaCierre(horario.horaCierre())
            .build())
        .toList();

    horarioTiendaRepository.saveAll(horarios);
  }

  private void validarDatosPorRol(SolicitudRegistro solicitud) {
    boolean esAdministrador = solicitud.rol() == RolRegistro.ADMIN_TIENDA;

    if (esAdministrador && solicitud.tienda() == null) {
      throw new SolicitudRegistroInvalidaException(
          "Los datos de la tienda son obligatorios para un administrador");
    }

    if (!esAdministrador && solicitud.tienda() != null) {
      throw new SolicitudRegistroInvalidaException(
          "Solo un administrador puede registrar una tienda");
    }
  }

  private void validarHorarios(List<SolicitudHorarioRegistro> horarios) {
    Set<Integer> diasRegistrados = new HashSet<>();

    for (SolicitudHorarioRegistro horario : horarios) {
      if (!diasRegistrados.add(horario.diaSemana())) {
        throw new SolicitudRegistroInvalidaException(
            "No se puede registrar dos veces el mismo día de la semana");
      }

      if (!horario.horaApertura().isBefore(horario.horaCierre())) {
        throw new SolicitudRegistroInvalidaException(
            "La hora de apertura debe ser anterior a la hora de cierre");
      }
    }
  }

  private String normalizarTelefono(String telefono, String propietario) {
    String digitos = telefono.replaceAll("\\D", "");
    if (digitos.length() != 10) {
      throw new SolicitudRegistroInvalidaException(
          "El teléfono " + propietario + " debe contener 10 dígitos");
    }
    return digitos;
  }

  private String normalizarOpcional(String valor) {
    if (valor == null || valor.isBlank()) {
      return null;
    }
    return valor.trim();
  }

  private String normalizarEmail(String email) {
    return email.trim().toLowerCase(Locale.ROOT);
  }
}
