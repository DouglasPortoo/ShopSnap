package com.douglasporto.ShopSnap.dto;

import java.io.Serializable;

import com.douglasporto.ShopSnap.services.validadions.ClienteInsert;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@ClienteInsert
public record ClienteNewDTO(
                            @NotEmpty(message = "preenchimento obrigatório")
                            @Size(min = 5, max = 120, message = "Nome deve ter entre 5 e 120 caracteres")
                            String nome, 
                            @NotEmpty(message = "preenchimento obrigatório")
                            @Email(message = "Email invalido")
                            String email, 
                            @NotEmpty(message = "preenchimento obrigatório")
                            String cpfOuCnpj, 
                            Integer tipo,
                            @NotEmpty(message = "preenchimento obrigatório")
                            String senha,
                            @NotEmpty(message = "preenchimento obrigatório")
                            String logradouro,
                            @NotEmpty(message = "preenchimento obrigatório")
                            String numero,
                            String complemento,
                            String bairro,
                            @NotEmpty(message = "preenchimento obrigatório")
                            String cep,
                            @NotEmpty(message = "preenchimento obrigatório")
                            String telefone1,
                            String telefone2,
                            String telefone3,
                            Integer cidadeId
                            )implements Serializable {

  private static final long serialVersionUID = 1L;



  
}
