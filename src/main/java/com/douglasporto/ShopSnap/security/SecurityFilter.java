package com.douglasporto.ShopSnap.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.douglasporto.ShopSnap.repositories.ClienteRepository;
import com.douglasporto.ShopSnap.services.TokenService;


@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  TokenService tokenService;

  @Autowired
  ClienteRepository clienteRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    var tokenJWT = this.recoverToken(request);

    if (tokenJWT != null) {

      var login = tokenService.validateToken(tokenJWT);

      var usuario = clienteRepository.findByEmail(login);

      var authoriies = usuario.getAuthorities();

      var authentication = new UsernamePasswordAuthenticationToken(usuario, null, authoriies);

      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }

  private String recoverToken(HttpServletRequest request) {
    var authorizationHeader = request.getHeader("Authorization");

    if (authorizationHeader != null) {
      return authorizationHeader.replace("Bearer ", "");
    }

    return null;
  }

}
