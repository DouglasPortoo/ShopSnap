package com.douglasporto.ShopSnap.dto;

import java.io.Serializable;

import com.douglasporto.ShopSnap.domain.Categoria;

public record CategoriaDTO(Integer id, String nome) implements Serializable {
  public CategoriaDTO(Categoria obj) {
    this(obj.getId(), obj.getNome());
  }

  private static final long serialVersionUID = 1L;
}
