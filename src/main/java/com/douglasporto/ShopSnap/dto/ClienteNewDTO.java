package com.douglasporto.ShopSnap.dto;

import java.io.Serializable;

public record ClienteNewDTO(
                            String nome, 
                            String email, 
                            String cpfOuCnpj, 
                            Integer tipo,
                            String logradouro,
                            String numero,
                            String complemento,
                            String bairro,
                            String cep,
                            String telefone1,
                            String telefone2,
                            String telefone3,
                            Integer cidadeId
                            )implements Serializable {

  private static final long serialVersionUID = 1L;



  
}
