package com.douglasporto.ShopSnap.dto;

import java.io.Serializable;

import com.douglasporto.ShopSnap.domain.Produto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record ProdutoDTO(Integer id,
    @NotEmpty(message = "Nome não pode ser vazio") 
    @Size(min = 5, max = 80, message = "Nome deve ter entre 5 e 80 caracteres") 
    String nome,
    Double preco)
    implements Serializable {

  private static final long serialVersionUID = 1L;
  
  public ProdutoDTO(Produto obj) {
    this(obj.getId(), obj.getNome(), obj.getPreco());
  }

}
