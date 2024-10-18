package com.douglasporto.ShopSnap.dto;

import jakarta.validation.constraints.NotBlank;

public record AutenticateDataDTO(@NotBlank(message = "Login é obrigatório") String login,

@NotBlank(message = "Senha é obrigatória") String password) {
  
}
