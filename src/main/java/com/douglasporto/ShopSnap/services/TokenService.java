package com.douglasporto.ShopSnap.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.douglasporto.ShopSnap.domain.Cliente;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

  @Value("${jwt.secret}")
  private String secret;

  public String generateToken(Cliente usuario) {
    try {
      var algorithm = Algorithm.HMAC256(secret);
      return JWT.create()
          .withIssuer("auth0")
          .withSubject(usuario.getEmail())
          .withClaim("id", usuario.getId().toString())
          .withExpiresAt(expireDate())
          .sign(algorithm);
    } catch (JWTCreationException exception) {
      throw new RuntimeException("erro ao gerar token", exception);
    }
  }

  public String validateToken(String tokenJWT) {
    try {
      var algorithm = Algorithm.HMAC256(secret);
      return JWT.require(algorithm)
          .withIssuer("auth0")
          .build()
          .verify(tokenJWT)
          .getSubject();

    } catch (JWTCreationException exception) {
      throw new RuntimeException("token invalido ou expirado!");
    }
  }

  private Instant expireDate() {
    return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
  }
}
