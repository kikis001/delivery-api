package com.pistolerosgalacticos.delivery_api.security;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

  private static final int LONGITUD_MINIMA_CLAVE = 32;

  private final JwtProperties properties;
  private final SecretKey claveFirma;

  public JwtService(JwtProperties properties) {
    this.properties = properties;

    byte[] clave = properties.secret().getBytes(StandardCharsets.UTF_8);
    if (clave.length < LONGITUD_MINIMA_CLAVE) {
      throw new IllegalArgumentException("JWT_SECRET debe contener al menos 32 bytes");
    }

    this.claveFirma = Keys.hmacShaKeyFor(clave);
  }

  public String generarToken(UsuarioPrincipal usuario) {
    Instant emitidoEn = Instant.now();
    Instant expiraEn = emitidoEn.plus(properties.expiration());

    return Jwts.builder()
        .setSubject(usuario.getUsername())
        .claim("uid", usuario.getId())
        .claim("rol", usuario.getRol().name())
        .setIssuedAt(Date.from(emitidoEn))
        .setExpiration(Date.from(expiraEn))
        .signWith(claveFirma, SignatureAlgorithm.HS256)
        .compact();
  }

  public Optional<String> obtenerSujetoValido(String token) {
    try {
      Claims claims = Jwts.parserBuilder()
          .setSigningKey(claveFirma)
          .build()
          .parseClaimsJws(token)
          .getBody();

      String sujeto = claims.getSubject();
      return sujeto == null || sujeto.isBlank()
          ? Optional.empty()
          : Optional.of(sujeto);
    } catch (JwtException | IllegalArgumentException excepcion) {
      return Optional.empty();
    }
  }
}
