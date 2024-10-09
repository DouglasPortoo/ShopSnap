package com.douglasporto.ShopSnap.dto;

import java.io.Serializable;


import com.douglasporto.ShopSnap.domain.Categoria;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CategoriaDTO(Integer id,
    @NotEmpty(message = "Nome não pode ser vazio") @Size(min = 5, max = 80, message = "Nome deve ter entre 5 e 80 caracteres") String nome)
    implements Serializable {

  private static final long serialVersionUID = 1L;

  public CategoriaDTO(Categoria obj) {
    this(obj.getId(), obj.getNome());
  }

}