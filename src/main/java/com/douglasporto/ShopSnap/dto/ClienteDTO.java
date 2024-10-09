package com.douglasporto.ShopSnap.dto;

import java.io.Serializable;

import com.douglasporto.ShopSnap.domain.Cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record ClienteDTO(Integer id,
    @NotEmpty(message = "preenchimento obrigatório") @Size(min = 5, max = 120, message = "Nome deve ter entre 5 e 120 caracteres") String nome,
    @NotEmpty(message = "preenchimento obrigatório") @Email(message = "Email invalido") String email)
    implements Serializable {

  private static final long serialVersionUID = 1L;

  public ClienteDTO(Cliente obj) {
    this(obj.getId(), obj.getNome(), obj.getEmail());
  }
}
