package com.douglasporto.ShopSnap.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douglasporto.ShopSnap.domain.Cliente;
import com.douglasporto.ShopSnap.dto.AutenticateDataDTO;
import com.douglasporto.ShopSnap.dto.JWTTokenDataDTO;
import com.douglasporto.ShopSnap.services.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticateController {

  @Autowired
  private AuthenticationManager manager;

  @Autowired
  private TokenService tokenService;

  @PostMapping
  public ResponseEntity<?> authenticate(@RequestBody @Valid AutenticateDataDTO dados) {
    var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.password());
    var authentication = manager.authenticate(authenticationToken);
    var tokenJWT = tokenService.generateToken((Cliente) authentication.getPrincipal());

    return ResponseEntity.ok(new JWTTokenDataDTO(tokenJWT));
  }
}
