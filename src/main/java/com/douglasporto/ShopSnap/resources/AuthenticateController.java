package com.douglasporto.ShopSnap.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douglasporto.ShopSnap.dto.AutenticateDataDTO;
import com.douglasporto.ShopSnap.dto.JWTTokenDataDTO;
import com.douglasporto.ShopSnap.repositories.ClienteRepository;
import com.douglasporto.ShopSnap.services.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticateController {

  @Autowired
  private TokenService tokenService;

  @Autowired
  ClienteRepository clienteRepository;

  @PostMapping
  public ResponseEntity<?> authenticate(@RequestBody @Valid AutenticateDataDTO body) {
    var user = this.clienteRepository.findByEmail(body.login());

    if (user == null) {
      return ResponseEntity.badRequest().build();
    }

    String token = tokenService.generateToken(user);

    return ResponseEntity.ok(new JWTTokenDataDTO(token));
  }
}
